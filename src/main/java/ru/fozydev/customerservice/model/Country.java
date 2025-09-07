package ru.fozydev.customerservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @Column(name = "country_guid", nullable = false)
    private UUID countryId;

    @Column(name = "code", nullable = false, length = 3)
    private String code;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    protected void onCreate() {

        if (countryId == null) {
            countryId = UUID.randomUUID();
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

    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    public Country() {
    }

    public Country(UUID countryId, String code, String name, Instant createdAt, Instant updatedAt, List<Customer> customers) {
        this.countryId = countryId;
        this.code = code;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customers = customers;
    }

    public UUID getCountryId() {
        return countryId;
    }

    public void setCountryId(UUID countryGuid) {
        this.countryId = countryGuid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String countryCode) {
        this.code = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String countryName) {
        this.name = countryName;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}