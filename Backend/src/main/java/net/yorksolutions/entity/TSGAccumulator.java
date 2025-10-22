package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Accumulator")
public class TSGAccumulator {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    public enum TSGAccumulatorType {
        DEDUCTIBLE,
        OOP_MAX
    }

    public enum TSGNetworkTier {
        IN_NETWORK,
        OUT_OF_NETWORK
    }

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    // DESC: Est. a Bi-Directional Many-to-One relationship w/ Enrollment
    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private TSGEnrollment enrollmentId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TSGAccumulatorType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TSGNetworkTier tier;

    @Column(nullable = false)
    private BigDecimal limitAmount;

    @Column(nullable = false)
    private BigDecimal usedAmount;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGAccumulator() {}

    /**
     * SECTION:
     * All Args Constructor
     * NOTE: Dually serves as Required Args Constructor
     */
    public TSGAccumulator(
            UUID id, TSGEnrollment enrollmentId, TSGAccumulatorType type,
            TSGNetworkTier tier, BigDecimal limitAmount, BigDecimal usedAmount
    ) {
        this.id = id;
        this.enrollmentId = enrollmentId;
        this.type = type;
        this.tier = tier;
        this.limitAmount = limitAmount;
        this.usedAmount = usedAmount;
    }

    /**
     * SECTION:
     * Getter/Setter Methods
     */
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TSGEnrollment getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(TSGEnrollment enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public TSGAccumulatorType getType() {
        return type;
    }

    public void setType(TSGAccumulatorType type) {
        this.type = type;
    }

    public TSGNetworkTier getTier() {
        return tier;
    }

    public void setTier(TSGNetworkTier tier) {
        this.tier = tier;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(BigDecimal limitAmount) {
        this.limitAmount = limitAmount;
    }

    public BigDecimal getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(BigDecimal usedAmount) {
        this.usedAmount = usedAmount;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGAccumulator that = (TSGAccumulator) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * SECTION:
     * Override for toString()
     */
    @Override
    public String toString() {
        return "TSGAccumulator{" +
                "id=" + id +
                '}';
    }
}