package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ClaimLine")
public class TSGClaimLine {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    // DESC: Est. a Bi-Directional Many-to-One relationship w/ Claim
    @ManyToOne
    @JoinColumn(name = "claim_id")
    private TSGClaim claimId;

    // DESC: Enforce an auto-increment-esque functionality
    // NOTE: This will entail getting the value of the last entry
    // ... PRIOR TO entry of a new entry
    @Column(nullable = false, unique = true, updatable = false)
    private Integer lineNumber;

    @Column(nullable = false)
    private String cptCode;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal billedAmount;

    @Column(nullable = false)
    private BigDecimal allowedAmount;

    @Column(nullable = false)
    private BigDecimal deductibleApplied;

    @Column(nullable = false)
    private BigDecimal copayApplied;

    @Column(nullable = false)
    private BigDecimal coinsuranceApplied;

    @Column(nullable = false)
    private BigDecimal planPaid;

    @Column(nullable = false)
    private BigDecimal memberResponsibility;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGClaimLine() { }

    /**
     * SECTION:
     * All Args Constructor
     * NOTE: Dually serves as Required Args Constructor
     */
    public TSGClaimLine(
            UUID id, TSGClaim claimId, Integer lineNumber, String cptCode,
            String description, BigDecimal billedAmount, BigDecimal allowedAmount,
            BigDecimal deductibleAmount, BigDecimal copayApplied, BigDecimal coinsuranceApplied,
            BigDecimal planPaid, BigDecimal memberResponsibility
    ) {
        this.id = id;
        this.claimId = claimId;
        this.lineNumber = lineNumber;
        this.cptCode = cptCode;
        this.description = description;
        this.billedAmount = billedAmount;
        this.allowedAmount = allowedAmount;
        this.deductibleApplied = deductibleApplied;
        this.copayApplied = copayApplied;
        this.coinsuranceApplied = coinsuranceApplied;
        this.planPaid = planPaid;
        this.memberResponsibility = memberResponsibility;
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

    public TSGClaim getClaimId() {
        return claimId;
    }

    public void setClaimId(TSGClaim claimId) {
        this.claimId = claimId;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getCptCode() {
        return cptCode;
    }

    public void setCptCode(String cptCode) {
        this.cptCode = cptCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(BigDecimal billedAmount) {
        this.billedAmount = billedAmount;
    }

    public BigDecimal getAllowedAmount() {
        return allowedAmount;
    }

    public void setAllowedAmount(BigDecimal allowedAmount) {
        this.allowedAmount = allowedAmount;
    }

    public BigDecimal getDeductibleAmount() {
        return deductibleApplied;
    }

    public void setDeductibleAmount(BigDecimal deductibleApplied) {
        this.deductibleApplied = deductibleApplied;
    }

    public BigDecimal getCopayApplied() {
        return copayApplied;
    }

    public void setCopayApplied(BigDecimal copayApplied) {
        this.copayApplied = copayApplied;
    }

    public BigDecimal getCoinsuranceApplied() {
        return coinsuranceApplied;
    }

    public void setCoinsuranceApplied(BigDecimal coinsuranceApplied) {
        this.coinsuranceApplied = coinsuranceApplied;
    }

    public BigDecimal getPlanPaid() {
        return planPaid;
    }

    public void setPlanPaid(BigDecimal planPaid) {
        this.planPaid = planPaid;
    }

    public BigDecimal getMemberResponsibility() {
        return memberResponsibility;
    }

    public void setMemberResponsibility(BigDecimal memberResponsibility) {
        this.memberResponsibility = memberResponsibility;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGClaimLine that = (TSGClaimLine) o;
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
        return "TSGClaimLine{" +
                "id=" + id +
                ", cptCode='" + cptCode + '\'' +
                '}';
    }
}