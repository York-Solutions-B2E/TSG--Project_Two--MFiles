package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGMember;
import net.yorksolutions.entity.TSGUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TSGMemberRepository extends JpaRepository<TSGMember, UUID> {
    Optional<TSGMember> findByUserId(TSGUser userId);
    // JPA Built-In Method: Optional<TSGMember> findById(UUID id)
}