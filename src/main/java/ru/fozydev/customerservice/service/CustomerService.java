package ru.fozydev.customerservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fozydev.customerservice.dto.CustomerContactDetailsUpdateDTO;
import ru.fozydev.customerservice.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {

    public List<Customer> getAllCustomers();

    public Optional<Customer> getCustomerById(UUID id);

    public Customer createCustomer(Customer customer);

    public Customer updateCustomer(UUID id, CustomerContactDetailsUpdateDTO dto);

    Page<Customer> searchCustomers(String fullName, UUID countryId, Pageable pageable);
}