package ru.fozydev.customerservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.dto.CustomerCreateRequestDTO;
import ru.fozydev.customerservice.dto.CustomerResponseDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;
import ru.fozydev.customerservice.mapper.customer.CustomerConverter;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.service.customer.CustomerService;

import java.util.List;
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

    public CustomerResponseDTO createCustomer(CustomerCreateRequestDTO dto) {

        CreateCustomerCommand command = new CreateCustomerCommand(

                dto.getCustomer().getName(),
                dto.getCustomer().getSurname(),
                dto.getContact().getEmail(),
                dto.getContact().getTelegramId(),
                dto.getCountry().getCountryId()
        );
        Customer created = customerService.createCustomer(command);
        return customerConverter.toResponseDTO(created);
    }

    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerCreateRequestDTO dto) {

        UpdateCustomerCommand command = new UpdateCustomerCommand(

                dto.getCustomer() != null ? dto.getCustomer().getName() : null,
                dto.getCustomer() != null ? dto.getCustomer().getSurname() : null,
                dto.getContact() != null ? dto.getContact().getEmail() : null,
                dto.getContact() != null ? dto.getContact().getTelegramId() : null,
                dto.getCountry() != null ? dto.getCountry().getCountryId() : null
        );
        Customer updated = customerService.updateCustomer(customerId, command);
        return customerConverter.toResponseDTO(updated);
    }

    public Page<CustomerDTO> searchCustomers(String fullName, UUID countryId, Pageable pageable) {
        Page<Customer> customers = customerService.searchCustomers(fullName, countryId, pageable);
        return customers.map(customerConverter::toDto);
    }
}
