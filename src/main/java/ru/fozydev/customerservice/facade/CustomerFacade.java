package ru.fozydev.customerservice.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.dto.customer.CustomerRequestDTO;
import ru.fozydev.customerservice.dto.customer.CustomerResponseDTO;
import ru.fozydev.customerservice.mapper.customer.CustomerCommandMapper;
import ru.fozydev.customerservice.mapper.customer.CustomerConverter;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.service.customer.CustomerService;

import java.util.List;
import java.util.UUID;

@Component
public class CustomerFacade {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;
    private final CustomerCommandMapper customerCommandMapper;

    @Autowired
    public CustomerFacade(CustomerService customerService, CustomerConverter customerConverter, CustomerCommandMapper customerCommandMapper) {
        this.customerService = customerService;
        this.customerConverter = customerConverter;
        this.customerCommandMapper = customerCommandMapper;
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
        CreateCustomerCommand command = customerCommandMapper.buildCreateCommand(dto);
        Customer created = customerService.createCustomer(command);
        return customerConverter.toResponseDTO(created);
    }

    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerRequestDTO dto) {
        UpdateCustomerCommand command = customerCommandMapper.buildUpdateCommand(dto);
        Customer updated = customerService.updateCustomer(customerId, command);
        return customerConverter.toResponseDTO(updated);
    }

    public Page<CustomerResponseDTO> searchCustomers(String fullName, UUID countryId, Pageable pageable) {
        Page<Customer> customers = customerService.searchCustomers(fullName, countryId, pageable);
        return customers.map(customerConverter::toResponseDTO);
    }
}