package net.yorksolutions.repository;

import net.yorksolutions.dto.ClaimListPOJO;
import net.yorksolutions.entity.TSGClaim;
import net.yorksolutions.entity.TSGMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public interface TSGClaimRepository extends JpaRepository<TSGClaim, UUID> {
    List<TSGClaim> findByMemberIdOrderByServiceStartDateAsc(TSGMember memberId);

    // DESC: Run a Native Query for the Filtering Logic
    @Query(
            value = "SELECT " +
                    "c.claim_number, c.service_start_date, c.service_end_date," +
                    "p.name, c.status, c.total_member_responsibility " +
                    "FROM claim AS c " + "INNER JOIN provider AS p ON c.provider_id = p.id " +
                    "WHERE status IN (:statusList) AND " +
                    "c.service_start_date >= COALESCE(:serviceStartDate, c.service_start_date) AND " +
                    "c.service_end_date <= COALESCE(:serviceEndDate, CURRENT_DATE) AND " +
                    "p.name ILIKE COALESCE(:name, p.name) AND " +
                    "c.claim_number = COALESCE(:claimNumber, c.claim_number) " +
                    "ORDER BY c.received_date DESC;", nativeQuery = true
    )

    // DESC: Dump received values to a List of Object fixed-length arrays
    // NOTE: I am using unorthodox naming, intentionally, to bypass
    // ... the Automatic Query Creation of JPA
    List<Object[]> gatherListOfClaimsNative(
            @Param("statusList") List<String> statusList,
            @Param("serviceStartDate") LocalDate serviceStartDate,
            @Param("serviceEndDate") LocalDate serviceEndDate,
            @Param("name") String name,
            @Param("claimNumber") String claimNumber
    );

    // DESC: Convert each Object entry to a ClaimListPOJO
    // NOTE: I am using unorthodox naming, intentionally, to bypass
    // ... the Automatic Query Creation of JPA
    default List<ClaimListPOJO> gatherListOfClaims(
            List<String> statusList, LocalDate serviceStartDate,
            LocalDate serviceEndDate, String name, String claimNumber
    ) {
        return gatherListOfClaimsNative(
                statusList, serviceStartDate, serviceEndDate, name, claimNumber
        ).stream()
                .map(row -> new ClaimListPOJO(
                        (String) row[0],
                        ((Date) row[1]).toLocalDate(),
                        ((Date) row[2]).toLocalDate(),
                        (String) row[3],
                        (String) row[4],
                        (BigDecimal) row[5]
                )).collect(Collectors.toList());
    }
}

/*
    @Query(
            value = "SELECT " +
                    "c.claim_number, c.service_start_date, c.service_end_date," +
                    "p.name, c.status, c.total_member_responsibility " +
                    "FROM claim AS c " + "INNER JOIN provider AS p ON c.provider_id = p.id " +
                    "WHERE status IN ('PROCESSED') AND " +
                    "c.service_start_date >= COALESCE(NULL, c.service_start_date) AND " +
                    "c.service_end_date <= COALESCE(NULL, CURRENT_DATE) AND " +
                    "p.name ILIKE COALESCE(NULL, p.name) AND " +
                    "c.claim_number = COALESCE(NULL, c.claim_number) " +
                    "ORDER BY c.received_date DESC;", nativeQuery = true
    )
 */