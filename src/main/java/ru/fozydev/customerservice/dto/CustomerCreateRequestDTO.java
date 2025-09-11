package ru.fozydev.customerservice.dto;

import ru.fozydev.customerservice.dto.contactdetails.ContactDetailsDTO;
import ru.fozydev.customerservice.dto.country.CountryDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;

public class CustomerCreateRequestDTO {

    private CustomerDTO customer;
    private ContactDetailsDTO contact;
    private CountryDTO country;

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public ContactDetailsDTO getContact() {
        return contact;
    }

    public void setContact(ContactDetailsDTO contact) {
        this.contact = contact;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }
}