package ru.fozydev.customerservice.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.exceptions.EntityNotFoundException;
import ru.fozydev.customerservice.model.ContactDetails;
import ru.fozydev.customerservice.model.Country;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.repository.CountryRepository;
import ru.fozydev.customerservice.repository.CustomerRepository;
import ru.fozydev.customerservice.util.EntityNames;
import ru.fozydev.customerservice.util.RepositoryUtils;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CountryRepository countryRepository) {
        this.customerRepository = customerRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(UUID customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException(EntityNames.Customer, customerId));
    }

    @Override
    public Customer createCustomer(CreateCustomerCommand command) {
        // customer
        Customer customer = new Customer();
        customer.setName(command.name());
        customer.setSurname(command.surname());
        // country
        Country country = RepositoryUtils.findByIdOrThrow(countryRepository, command.countryId(), EntityNames.Country);
        customer.setCountry(country);
        // contact
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmail(command.email());
        contactDetails.setTelegramId(command.telegramId());
        customer.setContactDetails(contactDetails);

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(UUID customerId, UpdateCustomerCommand command) {
        // customer
        Customer customer = RepositoryUtils.findByIdOrThrow(customerRepository, customerId, EntityNames.Customer);
        if (command.name() != null) customer.setName(command.name());
        if (command.surname() != null) customer.setSurname(command.surname());
        // country
        if (command.countryId() != null) {
            Country country = RepositoryUtils.findByIdOrThrow(countryRepository, command.countryId(), EntityNames.Country);
            customer.setCountry(country);
        }
        // contact
        ContactDetails contact = customer.getContactDetails();
        if (command.email() != null) contact.setEmail(command.email());
        if (command.telegramId() != null) contact.setTelegramId(command.telegramId());

        return customerRepository.save(customer);
    }

    @Override
    public Page<Customer> searchCustomers(String fullName, UUID countryId, Pageable pageable) {
        return customerRepository.searchCustomers(fullName, countryId, pageable);
    }
}