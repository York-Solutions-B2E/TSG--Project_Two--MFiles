package net.yorksolutions.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ClaimListPOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     */
    // Table: Claim
    private String claimNumber;
    private LocalDate serviceStartDate;
    private LocalDate serviceEndDate;

    // Table: Provider
    private String name;

    // Table: Claim (continued)
    private String status;
    private BigDecimal totalMemberResponsibility;

    // DESC: Need to be able to accept a List from POST
    private List<String> statusList;

    /**
     * SECTION:
     * No-Args Constructor
     * NOTE: This is for Jackson to deserialize POST input
     */
    public ClaimListPOJO() {}

    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: The totalMemberResponsibility is not required on the
     * ... POST end-point
     */
    public ClaimListPOJO(
            String claimNumber, LocalDate serviceStartDate, LocalDate serviceEndDate,
            String name, List<String> statusList
    ) {
        this.claimNumber = claimNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.name = name;
        this.statusList = statusList;
    }

    /**
     * SECTION:
     * Required-Args Constructor
     * NOTE: This is for the returned JSON because the List is
     * ... not a necessary value for the return
     */
    public ClaimListPOJO(
            String claimNumber, LocalDate serviceStartDate, LocalDate serviceEndDate,
            String name, String status, BigDecimal totalMemberResponsibility
    ) {
        this.claimNumber = claimNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.name = name;
        this.status = status;
        this.totalMemberResponsibility = totalMemberResponsibility;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public ClaimListPOJO(
            String claimNumber, LocalDate serviceStartDate, LocalDate serviceEndDate,
            String name, String status, BigDecimal totalMemberResponsibility, List<String> statusList
    ) {
        this.claimNumber = claimNumber;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.name = name;
        this.status = status;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.statusList = statusList;
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

    public BigDecimal getTotalMemberResponsibility() {
        return totalMemberResponsibility;
    }

    public void setTotalMemberResponsibility(BigDecimal totalMemberResponsibility) {
        this.totalMemberResponsibility = totalMemberResponsibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<String> statusList) {
        this.statusList = statusList;
    }
}
