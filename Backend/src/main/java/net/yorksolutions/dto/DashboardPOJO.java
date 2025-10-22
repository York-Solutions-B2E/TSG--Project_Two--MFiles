package net.yorksolutions.dto;

import java.math.BigDecimal;
import java.util.List;

public class DashboardPOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     */
    // Table: Member
    private String memberName;

    // Table: Plan
    private String planName;
    private String planNetwork;
    private Integer planYear;

    // Table: Accumulator
    private String accumulatorDeductibleType;
    private BigDecimal accumulatorDeductibleLimitAmount;
    private BigDecimal accumulatorDeductibleUsedAmount;
    private String accumulatorOopMaxType;
    private BigDecimal accumulatorOopMaxLimitAmount;
    private BigDecimal accumulatorOopMaxUsedAmount;

    // Table: Claim (must handle a list)
    private List<ClaimPOJO> claims;

    /**
     * SECTION:
     * No Args Constructor
     * NOTE: This enables me to initialize an empty Object that I back-fill
     * ... in the Controller
     */
    public DashboardPOJO() {}

    /**
     * SECTION:
     * All Args Constructor
     */
    public DashboardPOJO(
            String memberName, String planName, String planNetwork, Integer planYear,
            String accumulatorDeductibleType, BigDecimal accumulatorDeductibleLimitAmount,
            BigDecimal accumulatorDeductibleUsedAmount, String accumulatorOopMaxType,
            BigDecimal accumulatorOopMaxLimitAmount, BigDecimal accumulatorOopMaxUsedAmount,
            List<ClaimPOJO> claims
    ) {
        this.memberName = memberName;
        this.planName = planName;
        this.planNetwork = planNetwork;
        this.planYear = planYear;
        this.accumulatorDeductibleType = accumulatorDeductibleType;
        this.accumulatorDeductibleLimitAmount = accumulatorDeductibleLimitAmount;
        this.accumulatorDeductibleUsedAmount = accumulatorDeductibleUsedAmount;
        this.accumulatorOopMaxType = accumulatorOopMaxType;
        this.accumulatorOopMaxLimitAmount = accumulatorOopMaxLimitAmount;
        this.accumulatorOopMaxUsedAmount = accumulatorOopMaxUsedAmount;
        this.claims = claims;
    }

    /**
     * SECTION:
     * Getters and Setters
     */
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getPlanNetwork() {
        return planNetwork;
    }

    public void setPlanNetwork(String planNetwork) {
        this.planNetwork = planNetwork;
    }

    public Integer getPlanYear() {
        return planYear;
    }

    public void setPlanYear(Integer planYear) {
        this.planYear = planYear;
    }

    public String getAccumulatorDeductibleType() {
        return accumulatorDeductibleType;
    }

    public void setAccumulatorDeductibleType(String accumulatorDeductibleType) {
        this.accumulatorDeductibleType = accumulatorDeductibleType;
    }

    public BigDecimal getAccumulatorDeductibleLimitAmount() {
        return accumulatorDeductibleLimitAmount;
    }

    public void setAccumulatorDeductibleLimitAmount(BigDecimal accumulatorDeductibleLimitAmount) {
        this.accumulatorDeductibleLimitAmount = accumulatorDeductibleLimitAmount;
    }

    public BigDecimal getAccumulatorDeductibleUsedAmount() {
        return accumulatorDeductibleUsedAmount;
    }

    public void setAccumulatorDeductibleUsedAmount(BigDecimal accumulatorDeductibleUsedAmount) {
        this.accumulatorDeductibleUsedAmount = accumulatorDeductibleUsedAmount;
    }

    public String getAccumulatorOopMaxType() {
        return accumulatorOopMaxType;
    }

    public void setAccumulatorOopMaxType(String accumulatorOopMaxType) {
        this.accumulatorOopMaxType = accumulatorOopMaxType;
    }

    public BigDecimal getAccumulatorOopMaxLimitAmount() {
        return accumulatorOopMaxLimitAmount;
    }

    public void setAccumulatorOopMaxLimitAmount(BigDecimal accumulatorOopMaxLimitAmount) {
        this.accumulatorOopMaxLimitAmount = accumulatorOopMaxLimitAmount;
    }

    public BigDecimal getAccumulatorOopMaxUsedAmount() {
        return accumulatorOopMaxUsedAmount;
    }

    public void setAccumulatorOopMaxUsedAmount(BigDecimal accumulatorOopMaxUsedAmount) {
        this.accumulatorOopMaxUsedAmount = accumulatorOopMaxUsedAmount;
    }

    public List<ClaimPOJO> getClaims() {
        return claims;
    }

    public void setClaims(List<ClaimPOJO> claims) {
        this.claims = claims;
    }
}
