package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGClaim;
import net.yorksolutions.entity.TSGMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TSGClaimRepository extends JpaRepository<TSGClaim, UUID> {
    List<TSGClaim> findByMemberIdOrderByServiceStartDateAsc(TSGMember memberId);

    // DESC: Run a Native Query for the Filtering Logic
    // NOTE: The 'countQuery' attribute enables Spring to get
    // ... the size of the query, which is necessary for Pagination
    // ... and using Native Queries makes it harder for Spring to
    // ... do this without the attribute
    @Query(
            value = "SELECT " +
                    "c.claim_number, c.service_start_date, c.service_end_date," +
                    "p.name, c.status, c.total_member_responsibility " +
                    "FROM claim AS c " + "INNER JOIN provider AS p ON c.provider_id = p.id " +
                    "WHERE member_id = :memberId AND " +
                    "status IN (:statusList) AND " +
                    "c.service_start_date >= COALESCE(:serviceStartDate, c.service_start_date) AND " +
                    "c.service_end_date <= COALESCE(:serviceEndDate, CURRENT_DATE) AND " +
                    "p.name ILIKE COALESCE(:name, p.name) AND " +
                    "c.claim_number = COALESCE(:claimNumber, c.claim_number) " +
                    "ORDER BY c.received_date DESC;",
            countQuery = "SELECT COUNT(*) " +
                    "FROM claim AS c " + "INNER JOIN provider AS p ON c.provider_id = p.id " +
                    "WHERE member_id = :memberId AND " +
                    "status IN (:statusList) AND " +
                    "c.service_start_date >= COALESCE(:serviceStartDate, c.service_start_date) AND " +
                    "c.service_end_date <= COALESCE(:serviceEndDate, CURRENT_DATE) AND " +
                    "p.name ILIKE COALESCE(:name, p.name) AND " +
                    "c.claim_number = COALESCE(:claimNumber, c.claim_number);",
            nativeQuery = true
    )

    // DESC: Dump received values to a List of Object fixed-length arrays
    // NOTE: I am using unorthodox naming, intentionally, to bypass
    // ... the Automatic Query Creation of JPA
    // NOTE: Passing the Pageable instance enables Spring to auto-append the
    // ... necessary LIMIT, OFFSET and ORDER BY clauses to a native query,
    // ... which means that the data is "pre-paginated" at the database-level
    // ... NOT the Java/Spring-level (i.e., only the necessary page-worth of
    // ... data is returned)
    // NOTE: This RETURNS a 'paged' subset of the data as an Array of Objects
    Page<Object[]> gatherListOfClaimsNativePaginated(
            @Param("memberId") UUID memberId,
            @Param("statusList") List<String> statusList,
            @Param("serviceStartDate") LocalDate serviceStartDate,
            @Param("serviceEndDate") LocalDate serviceEndDate,
            @Param("name") String name,
            @Param("claimNumber") String claimNumber,
            Pageable pageable
    );

    TSGClaim findByClaimNumberAndMemberId(String claimNumber, TSGMember member);
}