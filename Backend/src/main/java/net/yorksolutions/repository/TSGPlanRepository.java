package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGPlanRepository extends JpaRepository<TSGPlan, UUID> {
    // JPA Built-In Method: Optional<TSGPlan> findById(UUID id)
}