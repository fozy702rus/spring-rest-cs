package ru.fozydev.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fozydev.customerservice.dto.ContactDetailsUpdateDTO;
import ru.fozydev.customerservice.dto.CustomerContactDetailsUpdateDTO;
import ru.fozydev.customerservice.dto.CustomerUpdateDTO;
import ru.fozydev.customerservice.model.ContactDetails;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.repository.ContactDetailsRepository;
import ru.fozydev.customerservice.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private ContactDetailsRepository contactDetailsRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ContactDetailsRepository contactDetailsRepository) {
        this.customerRepository = customerRepository;
        this.contactDetailsRepository = contactDetailsRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(UUID id, CustomerContactDetailsUpdateDTO dto) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        CustomerUpdateDTO customerDTO = dto.getCustomerUpdateDTO();
        ContactDetailsUpdateDTO contactDetailsDTO = dto.getContactDetailsUpdateDTO();

        Optional.ofNullable(customerDTO.getName()).ifPresent(existingCustomer::setName);
        Optional.ofNullable(customerDTO.getSurname()).ifPresent(existingCustomer::setSurname);
        Optional.ofNullable(customerDTO.getCountry()).ifPresent(existingCustomer::setCountry);

        ContactDetails contactDetails = existingCustomer.getContactDetails();
        Optional.ofNullable(contactDetailsDTO.getEmail()).ifPresent(contactDetails::setEmail);
        Optional.ofNullable(contactDetailsDTO.getTelegramId()).ifPresent(contactDetails::setTelegramId);

        customerRepository.save(existingCustomer);
        contactDetailsRepository.save(contactDetails);

        return existingCustomer;
    }

    @Override
    public Optional<Customer> getCustomerById(UUID id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Page<Customer> searchCustomers(String fullName, UUID countryId, Pageable pageable) {
        return customerRepository.searchCustomers(fullName, countryId, pageable);
    }
}