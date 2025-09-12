package ru.fozydev.customerservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.dto.CustomerRequestDTO;
import ru.fozydev.customerservice.dto.CustomerResponseDTO;
import ru.fozydev.customerservice.dto.contactdetails.ContactDetailsDTO;
import ru.fozydev.customerservice.dto.country.CountryDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;
import ru.fozydev.customerservice.mapper.customer.CustomerConverter;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.service.customer.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerFacade {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @Autowired
    public CustomerFacade(CustomerService customerService, CustomerConverter customerConverter) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
    }

    public CustomerResponseDTO getCustomerDTO(UUID customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return customerConverter.toResponseDTO(customer);
    }

    public List<CustomerResponseDTO> getCustomersDTO() {
        List<Customer> customers = customerService.getCustomers();
        return customerConverter.toResponseDTOList(customers);
    }

    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {
        CreateCustomerCommand command = buildCreateCommand(dto);
        Customer created = customerService.createCustomer(command);
        return customerConverter.toResponseDTO(created);
    }

    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerRequestDTO dto) {
        UpdateCustomerCommand command = buildUpdateCommand(dto);
        Customer updated = customerService.updateCustomer(customerId, command);
        return customerConverter.toResponseDTO(updated);
    }

    public Page<CustomerResponseDTO> searchCustomers(String fullName, UUID countryId, Pageable pageable) {
        Page<Customer> customers = customerService.searchCustomers(fullName, countryId, pageable);
        return customers.map(customerConverter::toResponseDTO);
    }

    private CreateCustomerCommand buildCreateCommand(CustomerRequestDTO dto) {
        return new CreateCustomerCommand(
                safeGetName(dto.getCustomerDTO()),
                safeGetSurname(dto.getCustomerDTO()),
                safeGetEmail(dto.getContactDetailsDTO()),
                safeGetTelegramId(dto.getContactDetailsDTO()),
                safeGetCountryId(dto.getCountryDTO())
        );
    }

    private UpdateCustomerCommand buildUpdateCommand(CustomerRequestDTO dto) {
        return new UpdateCustomerCommand(
                safeGetName(dto.getCustomerDTO()),
                safeGetSurname(dto.getCustomerDTO()),
                safeGetEmail(dto.getContactDetailsDTO()),
                safeGetTelegramId(dto.getContactDetailsDTO()),
                safeGetCountryId(dto.getCountryDTO())
        );
    }

    private String safeGetName(CustomerDTO customerDTO) {
        return Optional.ofNullable(customerDTO).map(CustomerDTO::getName).orElse(null);
    }

    private String safeGetSurname(CustomerDTO customerDTO) {
        return Optional.ofNullable(customerDTO).map(CustomerDTO::getSurname).orElse(null);
    }

    private String safeGetEmail(ContactDetailsDTO contactDTO) {
        return Optional.ofNullable(contactDTO).map(ContactDetailsDTO::getEmail).orElse(null);
    }

    private String safeGetTelegramId(ContactDetailsDTO contactDTO) {
        return Optional.ofNullable(contactDTO).map(ContactDetailsDTO::getTelegramId).orElse(null);
    }

    private UUID safeGetCountryId(CountryDTO countryDTO) {
        return Optional.ofNullable(countryDTO).map(CountryDTO::getCountryId).orElse(null);
    }
}