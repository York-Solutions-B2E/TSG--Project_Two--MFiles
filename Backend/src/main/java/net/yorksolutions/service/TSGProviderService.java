package net.yorksolutions.service;

import net.yorksolutions.entity.TSGProvider;
import net.yorksolutions.repository.TSGProviderRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TSGProviderService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGProviderRepository tsgProviderRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGProviderService(TSGProviderRepository tsgProviderRepository) {
        this.tsgProviderRepository = tsgProviderRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGProvider findById(UUID id) {
        Optional<TSGProvider> providerOptional = tsgProviderRepository.findById(id);
        return providerOptional.orElse(null);
    }
}