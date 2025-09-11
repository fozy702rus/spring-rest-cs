package ru.fozydev.customerservice.service.customer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    public List<Customer> getCustomers();

    public Customer getCustomerById(UUID id);

    public Customer createCustomer(CreateCustomerCommand command);

    public Customer updateCustomer(UUID customerId, UpdateCustomerCommand command);

    public Page<Customer> searchCustomers(String fullName, UUID countryId, Pageable pageable);
}