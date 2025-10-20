package net.yorksolutions.repository;

import net.yorksolutions.entity.TSGAccumulator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TSGAccumulatorRepository extends JpaRepository <TSGAccumulator, UUID> {}