package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TSGUserRepository extends JpaRepository<TSGUser, UUID> {
    Optional<TSGUser> findByEmail(String email);
}