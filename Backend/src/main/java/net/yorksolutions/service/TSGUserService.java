package net.yorksolutions.service;

import net.yorksolutions.entity.TSGUser;
import net.yorksolutions.repository.TSGUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TSGUserService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGUserRepository tsgUserRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGUserService(TSGUserRepository tsgUserRepository) {
        this.tsgUserRepository = tsgUserRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGUser findUserByEmail(String email) {
        Optional<TSGUser> currentUserOptional = tsgUserRepository.findByEmail(email);
        return currentUserOptional.orElse(null);
    }
}