package net.yorksolutions.service;

import net.yorksolutions.entity.TSGAccumulator;
import net.yorksolutions.entity.TSGEnrollment;
import net.yorksolutions.repository.TSGAccumulatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TSGAccumulatorService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGAccumulatorRepository tsgAccumulatorRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGAccumulatorService(TSGAccumulatorRepository tsgAccumulatorRepository) {
        this.tsgAccumulatorRepository = tsgAccumulatorRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGAccumulator findByEnrollmentIdAndType(TSGEnrollment enrollment, TSGAccumulator.TSGAccumulatorType type) {
        Optional<TSGAccumulator> currentAccumulatorOptional = tsgAccumulatorRepository.findByEnrollmentIdAndType(enrollment, type);
        return  currentAccumulatorOptional.orElse(null);
    }
}