package net.yorksolutions.service;

import net.yorksolutions.entity.TSGEnrollment;
import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.entity.TSGUser;
import net.yorksolutions.repository.TSGEnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TSGEnrollmentService {
    /**
     * SECTION:
     * Repository Declaration
     */
    private final TSGEnrollmentRepository tsgEnrollmentRepository;

    /**
     * SECTION:
     * Required-Args Constructor
     */
    public TSGEnrollmentService(TSGEnrollmentRepository tsgEnrollmentRepository) {
        this.tsgEnrollmentRepository = tsgEnrollmentRepository;
    }

    /**
     * SECTION:
     * Controller-Service Methods
     */
    public TSGEnrollment findByMemberIdAndActive(TSGMember member, Boolean active) {
        Optional<TSGEnrollment> currentEnrollmentOptional = tsgEnrollmentRepository.findByMemberIdAndActive(member, active);
        return currentEnrollmentOptional.orElse(null);
    }


    /*
            Optional<TSGMember> currentMemberOptional = tsgMemberRepository.findById(id);
        return currentMemberOptional.orElse(null);
     */

}