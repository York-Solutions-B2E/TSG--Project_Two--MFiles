package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGProviderRepository extends JpaRepository<TSGProvider, UUID> {
    // JPA Built-In Method: TSGProvider findById(UUID id)
}