package net.yorksolutions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "TSGUser")
public class TSGUser {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(columnDefinition = "CHARACTER VARYING DEFAULT 'google'", nullable = false)
    private String authProvider;

    @Column(columnDefinition = "CHARACTER VARYING DEFAULT 'not-applicable'", nullable = false)
    private String authSub;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false)
    private OffsetDateTime updatedAt;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGUser() {}

    /**
     * SECTION:
     * All Args Constructor
     * NOTE: Dually serves as Required Args Constructor
     */
    public TSGUser(
            UUID id, String authProvider, String authSub,
            String email, OffsetDateTime createdAt,
            OffsetDateTime updatedAt
    ) {
        this.id = id;
        this.authProvider = authProvider;
        this.authSub = authSub;
        this.email = email;
        this.createdAt = createdAt;
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

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getAuthSub() {
        return authSub;
    }

    public void setAuthSub(String authSub) {
        this.authSub = authSub;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
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

        TSGUser tsgUser = (TSGUser) o;
        return id.equals(tsgUser.id);
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
        return "TSGUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                '}';
    }
}
