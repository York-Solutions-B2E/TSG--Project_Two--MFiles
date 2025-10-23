package net.yorksolutions.dto;

import java.math.BigDecimal;

public class ClaimLinePOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     */
    // Table: ClaimStatusEvent
    private String cptCode;
    private String description;
    private BigDecimal billedAmount;
    private BigDecimal allowedAmount;
    private BigDecimal deductibleApplied;
    private BigDecimal copayApplied;
    private BigDecimal coinsuranceApplied;
    private BigDecimal memberResponsibility;

    /**
     * SECTION:
     * No-Args Constructor
     */
    public ClaimLinePOJO() {}

    /**
     * SECTION:
     * All Args Constructor
     */
    public ClaimLinePOJO(
            String cptCode, String description, BigDecimal billedAmount,
            BigDecimal allowedAmount, BigDecimal deductibleApplied,
            BigDecimal copayApplied, BigDecimal coinsuranceApplied,
            BigDecimal memberResponsibility
    ) {
        this.cptCode = cptCode;
        this.description = description;
        this.billedAmount = billedAmount;
        this.allowedAmount = allowedAmount;
        this.deductibleApplied = deductibleApplied;
        this.copayApplied = copayApplied;
        this.coinsuranceApplied = coinsuranceApplied;
        this.memberResponsibility = memberResponsibility;
    }

    /**
     * SECTION:
     * Getter / Setter Methods
     */
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

    public BigDecimal getDeductibleApplied() {
        return deductibleApplied;
    }

    public void setDeductibleApplied(BigDecimal deductibleApplied) {
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

    public BigDecimal getMemberResponsibility() {
        return memberResponsibility;
    }

    public void setMemberResponsibility(BigDecimal memberResponsibility) {
        this.memberResponsibility = memberResponsibility;
    }
}
