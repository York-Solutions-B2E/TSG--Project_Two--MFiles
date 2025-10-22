package net.yorksolutions.dto;

import java.time.LocalDate;
import java.util.List;

public class PaginatedClaimListPOJO extends ClaimListPOJO {
    /**
     * SECTION:
     * Declare DTO / POJO Variables
     * NOTE: These are Pagination-specific values that ClaimListPOJO
     * ... does not have
     */
    // DESC: Add Pagination-specific values to ClaimListPOJO
    private Integer pageNumber;
    private Integer pageSize;

    /**
     * SECTION:
     * No-Args Constructor
     * NOTE: This is for Jackson to deserialize POST input
     */
    public PaginatedClaimListPOJO() {}

    /**
     * SECTION:
     * All/Required-Args Constructor
     */
    public PaginatedClaimListPOJO(
            String claimNumber, LocalDate serviceStartDate, LocalDate serviceEndDate,
            String name, List<String> statusList, Integer pageNumber, Integer pageSize
    ) {
        super(claimNumber, serviceStartDate, serviceEndDate, name, statusList);
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    /**
     * SECTION:
     * Getter / Setter Methods
     */
    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
