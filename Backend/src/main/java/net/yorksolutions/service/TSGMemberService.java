package net.yorksolutions.service;

import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.entity.TSGUser;
import net.yorksolutions.repository.TSGMemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TSGMemberService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGMemberRepository tsgMemberRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGMemberService(TSGMemberRepository tsgMemberRepository) {
        this.tsgMemberRepository = tsgMemberRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGMember findByUserId(TSGUser tsgUser) {
        Optional<TSGMember> currentMemberOptional = tsgMemberRepository.findByUserId(tsgUser);
        return currentMemberOptional.orElse(null);
    }
}