package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGEnrollmentRepository extends JpaRepository<TSGEnrollment, UUID> {}