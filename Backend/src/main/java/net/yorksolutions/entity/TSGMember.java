package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Member")
public class TSGMember {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    // DESC: Est. a Uni-Directional One-to-One relationship w/ User
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private TSGUser userId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Embedded
    private TSGAddress mailingAddress;

    // DESC: Est. a Bi-Directional One-to-Many relationship with Enrollment
    @OneToMany(mappedBy = "memberId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TSGEnrollment> enrollments;

    // DESC: Helper-methods for Bi-Directional Member--Enrollment relationship
    public void addEnrollment(TSGEnrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.setMemberId(this);
    }
    public void removeEnrollment(TSGEnrollment enrollment) {
        enrollments.remove(enrollment);
        enrollment.setMemberId(null);
    }

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGMember() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGMember(
            UUID id, TSGUser userId, String firstName, String lastName,
            LocalDate dateOfBirth, String email, String phone, List<TSGEnrollment> enrollments
    ) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.enrollments = enrollments;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGMember(
            UUID id, TSGUser userId, String firstName,
            String lastName, LocalDate dateOfBirth,
            String email, String phone, TSGAddress mailingAddress,
            List<TSGEnrollment> enrollments
    ) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.mailingAddress = mailingAddress;
        this.enrollments = enrollments;
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

    public TSGUser getUserId() {
        return userId;
    }

    public void setUserId(TSGUser userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public TSGAddress getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(TSGAddress mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public List<TSGEnrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<TSGEnrollment> enrollments) {
        this.enrollments = enrollments;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGMember tsgMember = (TSGMember) o;
        return id.equals(tsgMember.id);
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
        return "TSGMember{" +
                "id=" + id +
                '}';
    }
}