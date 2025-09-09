package ru.fozydev.customerservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "customer_guid", nullable = false, length = 30)
    private UUID customerId;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "profileRefId", nullable = false)
    private UUID profileRefId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_guid")
    private Country country;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_details_guid")
    private ContactDetails contactDetails;

    @PrePersist
    protected void onCreate() {

        if (customerId == null) {
            customerId = UUID.randomUUID();
        }
        if (profileRefId == null) {
            profileRefId = UUID.randomUUID();
        }
        Instant now = Instant.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        Instant now = Instant.now();
        updatedAt = now;
    }

    public Customer() {
    }

    public Customer(UUID customerGuid, String name, String surname, UUID profileRefId, Instant createdAt, Instant updatedAt, Country country, ContactDetails contactDetails) {
        this.customerId = customerGuid;
        this.name = name;
        this.surname = surname;
        this.profileRefId = profileRefId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.country = country;
        this.contactDetails = contactDetails;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerGuid) {
        this.customerId = customerGuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UUID getProfileRefId() {
        return profileRefId;
    }

    public void setProfileRefId(UUID profileRefId) {
        this.profileRefId = profileRefId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(ContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }

}