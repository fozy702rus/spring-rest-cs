package ru.fozydev.customerservice.dto;

import ru.fozydev.customerservice.dto.contactdetails.ContactDetailsDTO;
import ru.fozydev.customerservice.dto.country.CountryDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;

public class CustomerRequestDTO {

    private CustomerDTO customerDTO;
    private ContactDetailsDTO contactDetailsDTO;
    private CountryDTO countryDTO;

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public ContactDetailsDTO getContactDetailsDTO() {
        return contactDetailsDTO;
    }

    public void setContactDetailsDTO(ContactDetailsDTO contactDetailsDTO) {
        this.contactDetailsDTO = contactDetailsDTO;
    }

    public CountryDTO getCountryDTO() {
        return countryDTO;
    }

    public void setCountryDTO(CountryDTO countryDTO) {
        this.countryDTO = countryDTO;
    }
}