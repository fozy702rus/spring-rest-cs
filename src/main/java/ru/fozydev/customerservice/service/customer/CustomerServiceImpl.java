package ru.fozydev.customerservice.service.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fozydev.customerservice.dto.operations.CustomerCreateRequest;
import ru.fozydev.customerservice.mapper.customer.CustomerMapper;
import ru.fozydev.customerservice.model.ContactDetails;
import ru.fozydev.customerservice.model.Country;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.repository.CountryRepository;
import ru.fozydev.customerservice.repository.CustomerRepository;
import ru.fozydev.customerservice.dto.operations.CustomerResponseDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CountryRepository countryRepository;
    private CustomerMapper customerMapper;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CountryRepository countryRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.countryRepository = countryRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerResponseDTO> getCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return customerMapper.toResponseList(customers);
    }

    @Override
    public CustomerResponseDTO getCustomerById(UUID id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer with id " + id + " not found"));
        return customerMapper.toResponse(customer);
    }

    @Override
    public CustomerResponseDTO createCustomer(CustomerCreateRequest dto) {

        // customer (имя и фамилия приходит из маппера)
        Customer customer = customerMapper.toCustomer(dto);

        // country
        Country country = countryRepository.findById(dto.getCountryDTO().getCountryId())
                .orElseThrow(() -> new RuntimeException("Country with id " + dto.getCountryDTO().getCountryId() + " not found"));
        customer.setCountry(country);

        // contactDetails
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.setEmail(dto.getContactDetailsDTO().getEmail());
        contactDetails.setTelegramId(dto.getContactDetailsDTO().getTelegramId());
        customer.setContactDetails(contactDetails);

        Customer saved = customerRepository.save(customer);

        return customerMapper.toResponse(saved);
        // отправлять вложенный JSON
    }

    @Override
    public CustomerResponseDTO updateCustomer(UUID customerId, CustomerCreateRequest dto) {

        // ищем по id нуба
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer with id" + customerId + " not found"));
        // customer info
        if (dto.getCustomerDTO() != null) {
            Optional.ofNullable(dto.getCustomerDTO().getName()).ifPresent(customer::setName);
            Optional.ofNullable(dto.getCustomerDTO().getSurname()).ifPresent(customer::setSurname);
        }
        // country info
        if (dto.getCountryDTO() != null) {
            Optional.ofNullable(dto.getCountryDTO().getCountryId()).ifPresent(countryId -> {
                Country country = countryRepository.findById(countryId)
                        .orElseThrow(() -> new RuntimeException("Country with id" + countryId + " not found"));
                customer.setCountry(country);
            });
        }
        // contactDetails info
        if (dto.getContactDetailsDTO() != null) {
            ContactDetails contactDetails = customer.getContactDetails();

            Optional.ofNullable(dto.getContactDetailsDTO().getEmail()).ifPresent(contactDetails::setEmail);
            Optional.ofNullable(dto.getContactDetailsDTO().getTelegramId()).ifPresent(contactDetails::setTelegramId);
        }
        return customerMapper.toResponse(customer);
        // отправлять вложенный JSON
    }

    @Override
    public Page<CustomerResponseDTO> searchCustomers(String fullName, UUID countryId, Pageable pageable) {

        Page<Customer> customersPage = customerRepository.searchCustomers(fullName, countryId, pageable);
        return customersPage.map(customerMapper::toResponse);
    }
}