package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Plan")
public class TSGPlan {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    public enum TSGPlanType{
        HMO,
        PPO,
        EPO,
        HDHP
    }

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TSGPlanType planType;

    @Column(nullable = false)
    private String networkName;

    @Column(nullable = false)
    private Integer planYear;

    @Column(nullable = false)
    private BigDecimal deductible;

    @Column(nullable = false)
    private BigDecimal outOutPocketMax;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGPlan() {}

    /**
     * SECTION:
     * All Args Constructor
     * NOTE: Dually serves as Required Args Constructor
     */
    public TSGPlan(
            UUID id, String name, TSGPlanType planType, String networkName,
            Integer planYear, BigDecimal deductible, BigDecimal outOutPocketMax
    ) {
        this.id = id;
        this.name = name;
        this.planType = planType;
        this.networkName = networkName;
        this.planYear = planYear;
        this.deductible = deductible;
        this.outOutPocketMax = outOutPocketMax;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TSGPlanType getPlanType() {
        return planType;
    }

    public void setPlanType(TSGPlanType planType) {
        this.planType = planType;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    public BigDecimal getDeductible() {
        return deductible;
    }

    public void setDeductible(BigDecimal deductible) {
        this.deductible = deductible;
    }

    public BigDecimal getOutOutPocketMax() {
        return outOutPocketMax;
    }

    public void setOutOutPocketMax(BigDecimal outOutPocketMax) {
        this.outOutPocketMax = outOutPocketMax;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGPlan tsgPlan = (TSGPlan) o;
        return id.equals(tsgPlan.id);
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
        return "TSGPlan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}