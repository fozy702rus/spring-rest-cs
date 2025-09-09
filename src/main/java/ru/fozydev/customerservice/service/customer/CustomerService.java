package ru.fozydev.customerservice.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fozydev.customerservice.dto.operations.CustomerCreateRequest;
import ru.fozydev.customerservice.dto.operations.CustomerResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    public List<CustomerResponseDTO> getCustomers();

    public CustomerResponseDTO getCustomerById(UUID id);

    public CustomerResponseDTO createCustomer(CustomerCreateRequest dto);

    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerCreateRequest dto);

    Page<CustomerResponseDTO> searchCustomers(String fullName, UUID countryId, Pageable pageable);
}