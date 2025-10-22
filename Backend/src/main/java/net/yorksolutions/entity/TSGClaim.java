package net.yorksolutions.entity;

import jakarta.persistence.*;
import net.yorksolutions.enumeration.TSGClaimStatus;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Claim")
public class TSGClaim {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    // DESC: Est. a Uni-Directional Many-to-One relationship w/ Member
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private TSGMember memberId;

    // DESC: Est. a Uni-Directional Many-to-One relationship w/ Provider
    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    private TSGProvider providerId;

    @Column(nullable = false)
    private LocalDate serviceStartDate;

    private LocalDate serviceEndDate;

    private LocalDate receivedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TSGClaimStatus status;

    @Column(nullable = false)
    private BigDecimal totalBilled;

    @Column(nullable = false)
    private BigDecimal totalAllowed;

    @Column(nullable = false)
    private BigDecimal totalPlanPaid;

    @Column(nullable = false)
    private BigDecimal totalMemberResponsibility;

    // DESC: Est. a Bi-Directional One-to-Many relationship with ClaimLine
    @OneToMany(mappedBy = "claimId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TSGClaimLine> lines;

    // DESC: Helper-methods for Bi-Directional Claim--ClaimLine relationship
    public void addLine(TSGClaimLine line) {
        lines.add(line);
        line.setClaimId(this);
    }
    public void removeLine(TSGClaimLine line) {
        lines.remove(line);
        line.setClaimId(null);
    }

    // DESC: Est. a Bi-Directional One-to-Many relationship with ClaimStatusEvent
    @OneToMany(mappedBy = "claimId",  cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "status_history")
    private List<TSGClaimStatusEvent> statusHistory;

    // DESC: Helper-methods for Bi-Directional Claim-ClaimStatusEvent relationship
    public void addStatusHistory(TSGClaimStatusEvent statHist) {
        statusHistory.add(statHist);
        statHist.setClaimId(this);
    }
    public void removeStatusHistory(TSGClaimStatusEvent statHist) {
        statusHistory.remove(statHist);
        statHist.setClaimId(null);
    }

    private OffsetDateTime updatedAt;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGClaim() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGClaim(
            UUID id, String claimNumber, TSGMember memberId, TSGProvider providerId,
            TSGClaimStatus status, BigDecimal totalBilled, BigDecimal totalAllowed,
            BigDecimal totalPlanPaid, BigDecimal totalMemberResponsibility,
            List<TSGClaimLine> lines, List<TSGClaimStatusEvent> statusHistory,
            LocalDate serviceStartDate
    ) {
        this.id = id;
        this.claimNumber = claimNumber;
        this.memberId = memberId;
        this.providerId = providerId;
        this.status = status;
        this.totalBilled = totalBilled;
        this.totalAllowed = totalAllowed;
        this.totalPlanPaid = totalPlanPaid;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.lines = lines;
        this.statusHistory = statusHistory;
        this.serviceStartDate = serviceStartDate;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGClaim(
            UUID id, String claimNumber, TSGMember memberId, TSGProvider providerId,
            LocalDate serviceStartDate, LocalDate serviceEndDate, LocalDate receivedDate,
            TSGClaimStatus status, BigDecimal totalBilled, BigDecimal totalAllowed,
            BigDecimal totalPlanPaid, BigDecimal totalMemberResponsibility,
            List<TSGClaimLine> lines, List<TSGClaimStatusEvent> statusHistory, OffsetDateTime updatedAt
    ) {
        this.id = id;
        this.claimNumber = claimNumber;
        this.memberId = memberId;
        this.providerId = providerId;
        this.serviceStartDate = serviceStartDate;
        this.serviceEndDate = serviceEndDate;
        this.receivedDate = receivedDate;
        this.status = status;
        this.totalBilled = totalBilled;
        this.totalAllowed = totalAllowed;
        this.totalPlanPaid = totalPlanPaid;
        this.totalMemberResponsibility = totalMemberResponsibility;
        this.lines = lines;
        this.statusHistory = statusHistory;
        this.updatedAt = updatedAt;
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

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public TSGMember getMemberId() {
        return memberId;
    }

    public void setMemberId(TSGMember memberId) {
        this.memberId = memberId;
    }

    public TSGProvider getProviderId() {
        return providerId;
    }

    public void setProviderId(TSGProvider providerId) {
        this.providerId = providerId;
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

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public TSGClaimStatus getStatus() {
        return status;
    }

    public void setStatus(TSGClaimStatus status) {
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

    public List<TSGClaimLine> getLines() {
        return lines;
    }

    public void setLines(List<TSGClaimLine> lines) {
        this.lines = lines;
    }

    public List<TSGClaimStatusEvent> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<TSGClaimStatusEvent> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGClaim tsgClaim = (TSGClaim) o;
        return id.equals(tsgClaim.id);
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
        return "TSGClaim{" +
                "id=" + id +
                ", claimNumber='" + claimNumber + '\'' +
                '}';
    }
}