package net.yorksolutions.dto;

import java.math.BigDecimal;

public class ClaimPOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     */
    // Table: Claim
    private String claimNumber;
    private String claimStatus;
    private BigDecimal claimTotalMemberResponsibility;

    /**
     * SECTION:
     * No Args Constructor
     */
    public ClaimPOJO() {}

    /**
     * SECTION:
     * All Args Constructor
     */
    public ClaimPOJO(String claimNumber, String claimStatus, BigDecimal claimTotalMemberResponsibility) {
        this.claimNumber = claimNumber;
        this.claimStatus = claimStatus;
        this.claimTotalMemberResponsibility = claimTotalMemberResponsibility;
    }

    /**
     * SECTION:
     * Getters and Setters
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public BigDecimal getClaimTotalMemberResponsibility() {
        return claimTotalMemberResponsibility;
    }

    public void setClaimTotalMemberResponsibility(BigDecimal claimTotalMemberResponsibility) {
        this.claimTotalMemberResponsibility = claimTotalMemberResponsibility;
    }
}
