package net.yorksolutions.service;

import net.yorksolutions.entity.TSGPlan;
import net.yorksolutions.repository.TSGPlanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TSGPlanService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGPlanRepository tsgPlanRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGPlanService(TSGPlanRepository tsgPlanRepository) {
        this.tsgPlanRepository = tsgPlanRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGPlan findById(UUID id) {
        Optional<TSGPlan> currentPlanOptional = tsgPlanRepository.findById(id);
        return currentPlanOptional.orElse(null);
    }
}