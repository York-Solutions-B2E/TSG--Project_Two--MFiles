package net.yorksolutions.service;

import net.yorksolutions.entity.TSGClaim;
import net.yorksolutions.entity.TSGClaimLine;
import net.yorksolutions.repository.TSGClaimLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TSGClaimLineService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGClaimLineRepository tsgClaimLineRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGClaimLineService(TSGClaimLineRepository tsgClaimLineRepository) {
        this.tsgClaimLineRepository = tsgClaimLineRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public List<TSGClaimLine> findByClaimId(TSGClaim claim) {
        return tsgClaimLineRepository.findByClaimId(claim);
    }
}