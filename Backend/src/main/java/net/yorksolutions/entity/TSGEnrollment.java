package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Enrollment")
public class TSGEnrollment {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    // DESC: Est. a Bi-Directional Many-to-One relationship w/ Member
    @ManyToOne
    @JoinColumn(name = "member_id")
    private TSGMember memberId;

    // DESC: Est. a Uni-Directional Many-to-One relationship w/ Plan
    @ManyToOne
    @JoinColumn(name = "plan_id", referencedColumnName = "id")
    private TSGPlan planId;

    @Column(nullable = false)
    private LocalDate coverageStart;

    private LocalDate coverageEnd;

    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT TRUE")
    private Boolean active;

    // DESC: Est. a Bi-Directional One-to-Many relationship w/ Accumulator
    @OneToMany(mappedBy = "enrollmentId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TSGAccumulator> accumulators;

    // DESC: Helper-methods for Bi-Directional Enrollment--Accumulator relationship
    public void addAccumulator(TSGAccumulator accumulator) {
        accumulators.add(accumulator);
        accumulator.setEnrollmentId(this);
    }
    public void removeAccumulator(TSGAccumulator accumulator) {
        accumulators.remove(accumulator);
        accumulator.setEnrollmentId(null);
    }

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGEnrollment() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGEnrollment(
            UUID id, TSGMember memberId, TSGPlan planId, LocalDate coverageStart,
            Boolean active, List<TSGAccumulator> accumulators
    ) {
        this.id = id;
        this.memberId = memberId;
        this.planId = planId;
        this.coverageStart = coverageStart;
        this.active = active;
        this.accumulators = accumulators;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGEnrollment(
            UUID id, TSGMember memberId, TSGPlan planId, LocalDate coverageStart,
            LocalDate coverageEnd, Boolean active, List<TSGAccumulator> accumulators
    ) {
        this.id = id;
        this.memberId = memberId;
        this.planId = planId;
        this.coverageStart = coverageStart;
        this.coverageEnd = coverageEnd;
        this.active = active;
        this.accumulators = accumulators;
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

    public TSGMember getMemberId() {
        return memberId;
    }

    public void setMemberId(TSGMember memberId) {
        this.memberId = memberId;
    }

    public TSGPlan getPlanId() {
        return planId;
    }

    public void setPlanId(TSGPlan planId) {
        this.planId = planId;
    }

    public LocalDate getCoverageStart() {
        return coverageStart;
    }

    public void setCoverageStart(LocalDate coverageStart) {
        this.coverageStart = coverageStart;
    }

    public LocalDate getCoverageEnd() {
        return coverageEnd;
    }

    public void setCoverageEnd(LocalDate coverageEnd) {
        this.coverageEnd = coverageEnd;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TSGAccumulator> getAccumulators() {
        return accumulators;
    }

    public void setAccumulators(List<TSGAccumulator> accumulators) {
        this.accumulators = accumulators;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGEnrollment that = (TSGEnrollment) o;
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
        return "TSGEnrollment{" +
                "id=" + id +
                '}';
    }
}