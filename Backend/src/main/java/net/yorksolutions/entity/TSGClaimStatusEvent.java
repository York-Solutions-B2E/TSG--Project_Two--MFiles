package net.yorksolutions.entity;

import jakarta.persistence.*;
import net.yorksolutions.enumeration.TSGClaimStatus;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "ClaimStatusEvent")
public class TSGClaimStatusEvent {
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TSGClaimStatus status;

    @Column(nullable = false)
    private OffsetDateTime occurredAt;

    private String note;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGClaimStatusEvent() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGClaimStatusEvent(
            UUID id, TSGClaim claimId, TSGClaimStatus status,
            OffsetDateTime occurredAt
    ) {
        this.id = id;
        this.claimId = claimId;
        this.status = status;
        this.occurredAt = occurredAt;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGClaimStatusEvent(
            UUID id, TSGClaim claimId, TSGClaimStatus status,
            OffsetDateTime occurredAt, String note
    ) {
        this.id = id;
        this.claimId = claimId;
        this.status = status;
        this.occurredAt = occurredAt;
        this.note = note;
    }

    /**
     * SECTION:
     * Getter/Setter Methods
     */
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public OffsetDateTime getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(OffsetDateTime occurredAt) {
        this.occurredAt = occurredAt;
    }

    public TSGClaimStatus getStatus() {
        return status;
    }

    public void setStatus(TSGClaimStatus status) {
        this.status = status;
    }

    public TSGClaim getClaimId() {
        return claimId;
    }

    public void setClaimId(TSGClaim claimId) {
        this.claimId = claimId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGClaimStatusEvent that = (TSGClaimStatusEvent) o;
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
        return "TSGClaimStatusEvent{" +
                "id=" + id +
                '}';
    }
}