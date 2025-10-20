package net.yorksolutions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class TSGAddress {
    /**
     * SECTION:
     * Variables, Annotations and Enums (as applicable)
     */
    @Column(nullable = false)
    private String line1;

    private String line2;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String postalCode;

    /**
     * SECTION:
     * No Args Constructor
     */
    public TSGAddress() {}

    /**
     * SECTION:
     * Required Args Constructor
     */
    public TSGAddress(String line1, String city, String state, String postalCode) {
        this.line1 = line1;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    /**
     * SECTION:
     * All Args Constructor
     */
    public TSGAddress(String line1, String line2, String city, String state, String postalCode) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    /**
     * SECTION:
     * Getter/Setter Methods
     */
    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * SECTION:
     * Overrides for equals() and hashCode()
     * NOTE: Research suggests that the default implementation is sufficient
     * ... when working with Embeddable beans
     */

    /**
     * SECTION:
     * Override for toString()
     */
    @Override
    public String toString() {
        return "TSGAddress{" +
                "line1='" + line1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}