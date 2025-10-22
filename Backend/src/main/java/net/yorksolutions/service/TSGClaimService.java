package net.yorksolutions.service;

import net.yorksolutions.dto.ClaimListPOJO;
import net.yorksolutions.entity.TSGClaim;
import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.repository.TSGClaimRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<ClaimListPOJO> findClaims(
            List<String> statusList, LocalDate serviceStartDate,
            LocalDate serviceEndDate, String name, String claimNumber
    ) {
        return tsgClaimRepository.gatherListOfClaims(
                statusList, serviceStartDate, serviceEndDate, name, claimNumber
        );
    }

    public Page<ClaimListPOJO> findClaimsPaginated(
            Pageable pageable, List<String> statusList, LocalDate serviceStartDate,
            LocalDate serviceEndDate, String name, String claimNumber
    ) {

    }
}