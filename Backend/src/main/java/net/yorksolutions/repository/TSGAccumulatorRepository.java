package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGAccumulator;
import net.yorksolutions.entity.TSGEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TSGAccumulatorRepository extends JpaRepository <TSGAccumulator, UUID> {
    Optional<TSGAccumulator> findByEnrollmentIdAndType(
            TSGEnrollment enrollment, TSGAccumulator.TSGAccumulatorType type
    );
}