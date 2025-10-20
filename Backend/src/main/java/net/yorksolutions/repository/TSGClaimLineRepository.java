package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGClaimLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGClaimLineRepository extends JpaRepository<TSGClaimLine, UUID> {}