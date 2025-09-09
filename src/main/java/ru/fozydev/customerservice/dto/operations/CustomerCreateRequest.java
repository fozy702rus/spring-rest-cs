package ru.fozydev.customerservice.dto.operations;

import ru.fozydev.customerservice.dto.contactdetails.ContactDetailsDTO;
import ru.fozydev.customerservice.dto.country.CountryDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;

public class CustomerCreateRequest {

    private CustomerDTO customerDTO;
    private CountryDTO countryDTO;
    private ContactDetailsDTO contactDetailsDTO;

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }

    public ContactDetailsDTO getContactDetailsDTO() {
        return contactDetailsDTO;
    }

    public void setContactDetailsDTO(ContactDetailsDTO contactDetailsDTO) {
        this.contactDetailsDTO = contactDetailsDTO;
    }
}