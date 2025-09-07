package ru.fozydev.customerservice.dto;

import ru.fozydev.customerservice.model.Country;

import java.util.UUID;

public class CustomerUpdateDTO {

    private UUID customerDtoId;
    private String name;
    private String surname;
    private Country country;

    public UUID getCustomerDtoId() {
        return customerDtoId;
    }

    public void setCustomerDtoId(UUID customerDtoId) {
        this.customerDtoId = customerDtoId;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}