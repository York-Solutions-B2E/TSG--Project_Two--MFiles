package net.yorksolutions.dto;

import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public class ClaimAndClaimLinePOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     */
    // Table: Claim
    private String claimNumber;

    // Table: Provider
    private String providerName;

    // Table: Claim (continued)
    private LocalDate serviceStartDate;
    private LocalDate serviceEndDate;
    private String status;
    private LocalDate receivedDate;
    private OffsetDateTime updatedAt;
    private BigDecimal totalBilled;
    private BigDecimal totalAllowed;
    private BigDecimal totalPlanPaid;
    private BigDecimal totalMemberResponsibility;

    // Table: ClaimStatusEvent
    private List<ClaimLinePOJO> claimLines;

    /**
     * SECTION:
     * No-Args Constructor
     */
    public ClaimAndClaimLinePOJO() {}

    /**
     * SECTION:
     * All Args Constructor
     */
    public ClaimAndClaimLinePOJO(
            String claimNumber, String providerName, LocalDate serviceStartDate,
            LocalDate serviceEndDate, String status, BigDecimal totalBilled,
            LocalDate receivedDate, OffsetDateTime updatedAt, BigDecimal totalAllowed,
            BigDecimal totalPlanPaid, BigDecimal totalMemberResponsibility,
            List<ClaimLinePOJO> claimLines
    ) {
        this.claimNumber = claimNumber;
        this.providerName = providerName;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.status = status;
        this.totalBilled = totalBilled;
        this.receivedDate = receivedDate;
        this.updatedAt = updatedAt;
        this.totalAllowed = totalAllowed;
        this.totalPlanPaid = totalPlanPaid;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.claimLines = claimLines;
    }

    /**
     * SECTION:
     * Getter / Setter Methods
     */
    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public LocalDate getServiceStartDate() {
        return serviceStartDate;
    }

    public void setServiceStartDate(LocalDate serviceStartDate) {
        this.serviceStartDate = serviceStartDate;
    }

    public LocalDate getServiceEndDate() {
        return serviceEndDate;
    }

    public void setServiceEndDate(LocalDate serviceEndDate) {
        this.serviceEndDate = serviceEndDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalBilled() {
        return totalBilled;
    }

    public void setTotalBilled(BigDecimal totalBilled) {
        this.totalBilled = totalBilled;
    }

    public BigDecimal getTotalAllowed() {
        return totalAllowed;
    }

    public void setTotalAllowed(BigDecimal totalAllowed) {
        this.totalAllowed = totalAllowed;
    }

    public BigDecimal getTotalPlanPaid() {
        return totalPlanPaid;
    }

    public void setTotalPlanPaid(BigDecimal totalPlanPaid) {
        this.totalPlanPaid = totalPlanPaid;
    }

    public BigDecimal getTotalMemberResponsibility() {
        return totalMemberResponsibility;
    }

    public void setTotalMemberResponsibility(BigDecimal totalMemberResponsibility) {
        this.totalMemberResponsibility = totalMemberResponsibility;
    }

    public List<ClaimLinePOJO> getClaimLines() {
        return claimLines;
    }

    public void setClaimLines(List<ClaimLinePOJO> claimLines) {
        this.claimLines = claimLines;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
