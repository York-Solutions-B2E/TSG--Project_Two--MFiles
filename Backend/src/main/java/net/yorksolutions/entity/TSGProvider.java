package net.yorksolutions.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "Provider")
public class TSGProvider {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "CHARACTER VARYING DEFAULT 'Generalist'", nullable = false)
    private String specialty;

    @Embedded
    private TSGAddress address;

    @Column(nullable = false)
    private String phone;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGProvider() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGProvider(UUID id, String name, String specialty, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGProvider(UUID id, String name, String specialty, TSGAddress address, String phone) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.address = address;
        this.phone = phone;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public TSGAddress getAddress() {
        return address;
    }

    public void setAddress(TSGAddress address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        TSGProvider that = (TSGProvider) o;
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
        return "TSGProvider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}