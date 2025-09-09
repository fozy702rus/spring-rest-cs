package ru.fozydev.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "contactdetails")
public class ContactDetails {

    @Id
    @Column(name = "contact_details_guid", nullable = false)
    private UUID contactDetailsId;

    @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Pattern(regexp = "^@([A-Za-z0-9_]{5,32})$")
    @Column(name = "telegram_id")
    private String telegramId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToOne(mappedBy = "contactDetails", fetch = FetchType.LAZY)
    private Customer customer;

    @PrePersist
    protected void onCreate() {

        if (contactDetailsId == null) {
            contactDetailsId = UUID.randomUUID();
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

    public ContactDetails() {
    }

    public ContactDetails(UUID contactDetailsId, String email, String telegramId, Instant createdAt, Instant updatedAt, Customer customer) {
        this.contactDetailsId = contactDetailsId;
        this.email = email;
        this.telegramId = telegramId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customer = customer;
    }

    public UUID getContactDetailsId() {
        return contactDetailsId;
    }

    public void setContactDetailsId(UUID contactDetailsGuid) {
        this.contactDetailsId = contactDetailsGuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}