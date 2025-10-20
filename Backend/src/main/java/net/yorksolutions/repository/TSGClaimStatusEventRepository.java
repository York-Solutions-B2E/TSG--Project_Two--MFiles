package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGClaimStatusEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGClaimStatusEventRepository extends JpaRepository<TSGClaimStatusEvent, UUID> {}