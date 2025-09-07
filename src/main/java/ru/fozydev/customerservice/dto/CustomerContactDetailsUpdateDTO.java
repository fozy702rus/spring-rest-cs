package ru.fozydev.customerservice.dto;

public class CustomerContactDetailsUpdateDTO {

    private CustomerUpdateDTO customerUpdateDTO;
    private ContactDetailsUpdateDTO contactDetailsUpdateDTO;

    public CustomerUpdateDTO getCustomerUpdateDTO() {
        return customerUpdateDTO;
    }

    public void setCustomerUpdateDTO(CustomerUpdateDTO customerUpdateDTO) {
        this.customerUpdateDTO = customerUpdateDTO;
    }

    public ContactDetailsUpdateDTO getContactDetailsUpdateDTO() {
        return contactDetailsUpdateDTO;
    }

    public void setContactDetailsUpdateDTO(ContactDetailsUpdateDTO contactDetailsUpdateDTO) {
        this.contactDetailsUpdateDTO = contactDetailsUpdateDTO;
    }
}