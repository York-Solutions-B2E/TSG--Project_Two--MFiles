package net.yorksolutions.service;

import net.yorksolutions.dto.ClaimListPOJO;
import net.yorksolutions.entity.TSGClaim;
import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.repository.TSGClaimRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Service
public class TSGClaimService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGClaimRepository tsgClaimRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGClaimService(TSGClaimRepository tsgClaimRepository) {
        this.tsgClaimRepository = tsgClaimRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public List<TSGClaim> findByMemberIdOrderByServiceStartDateAsc(TSGMember member) {
        return tsgClaimRepository.findByMemberIdOrderByServiceStartDateAsc(member);
    }

    public Page<ClaimListPOJO> findClaimsPaginated(
            UUID memberId, List<String> statusList, LocalDate serviceStartDate,
            LocalDate serviceEndDate, String name, String claimNumber,
            Pageable pageable
            ) {

        // DESC: Retrieve the paginated array of Objects from the repo's
        // ... native query
        Page<Object[]> paginatedResultsAsObj = tsgClaimRepository
                .gatherListOfClaimsNativePaginated(
                        memberId, statusList, serviceStartDate,
                        serviceEndDate, name, claimNumber, pageable
                );

        // DESC: Map/convert Array of Objects to instance of DTOs
        // NOTE: This creates a "functional paradigm" and, in this
        // ... scenario, the 'Function' receives an Object[] and
        // ... outputs a ClaimListPOJO
        // NOTE: This uses a Lambda Expression (an Anonymous Function)
        Function<Object[], ClaimListPOJO> arrayToDtoMapper = (rowOfObjectArray) -> {
            // DESC: Declare necessary Lambda-specific variables
            String cN = (String) rowOfObjectArray[0];
            LocalDate sSd = ((Date) rowOfObjectArray[1]).toLocalDate();
            LocalDate sEd = ((Date) rowOfObjectArray[2]).toLocalDate();
            String n = (String)  rowOfObjectArray[3];
            String s = (String) rowOfObjectArray[4];
            BigDecimal tMr = (BigDecimal) rowOfObjectArray[5];

            // DESC: Creates instance of POJO / DTO
            return new ClaimListPOJO(cN, sSd, sEd, n, s, tMr);
        };

        // DESC: Return a Page of POJOs
        // NOTE: These are directly converted from a Page of
        // ... Object-Arrays using `map()` to handle the input
        // ... stream
        return paginatedResultsAsObj.map(arrayToDtoMapper);
    }

    public TSGClaim findByClaimNumberAndMemberId(String claimNumber, TSGMember member) {
        return tsgClaimRepository.findByClaimNumberAndMemberId(claimNumber, member);
    }
}