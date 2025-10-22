package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGEnrollment;
import net.yorksolutions.entity.TSGMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TSGEnrollmentRepository extends JpaRepository<TSGEnrollment, UUID> {
    Optional<TSGEnrollment> findByMemberIdAndActive(TSGMember member, Boolean active);
}