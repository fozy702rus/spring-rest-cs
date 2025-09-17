package ru.fozydev.customerservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.exceptions.EntityNotFoundException;
import ru.fozydev.customerservice.model.Country;
import ru.fozydev.customerservice.model.Customer;
import ru.fozydev.customerservice.repository.CountryRepository;
import ru.fozydev.customerservice.repository.CustomerRepository;
import ru.fozydev.customerservice.service.customer.CustomerServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Test
    public void getCustomers_shouldReturnAllCustomers() {

        List<Customer> customers = List.of(new Customer());
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> result = customerServiceImpl.getCustomers();

        assertEquals(customers, result);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void getCustomer_shouldThrowExceptionWhenCustomerNotFound() {

        when(customerRepository.findAll()).thenReturn(Collections.emptyList());

        List<Customer> result = customerServiceImpl.getCustomers();

        assertEquals(Collections.emptyList(), result);
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void getCustomerById_shouldReturnCustomer() {

        Customer customer = new Customer();
        UUID customerId = UUID.randomUUID();
        customer.setCustomerId(customerId);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        Customer result = customerServiceImpl.getCustomerById(customerId);

        assertEquals(customer, result);
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void getCustomerById_shouldThrowExceptionWhenCustomerNotFound() {

        UUID customerId = UUID.randomUUID();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> customerServiceImpl.getCustomerById(customerId));

        assertEquals("Customer with id " + customerId + " not found", exception.getMessage());

        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void createCustomer_shouldCreateCustomer() {

        UUID countryId = UUID.randomUUID();
        CreateCustomerCommand command = new CreateCustomerCommand(
                "Name", "Surname", "name@example.com", "@username", countryId
        );

        Country country = new Country();
        country.setCountryId(countryId);
        when(countryRepository.findById(countryId)).thenReturn(Optional.of(country));


        when(customerRepository.save(Mockito.<Customer>any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Customer result = customerServiceImpl.createCustomer(command);

        assertEquals("Name", result.getName());
        assertEquals("Surname", result.getSurname());
        assertEquals("name@example.com", result.getContactDetails().getEmail());
        assertEquals("@username", result.getContactDetails().getTelegramId());
        assertEquals(countryId, result.getCountry().getCountryId());

        verify(countryRepository, times(1)).findById(countryId);
        verify(customerRepository, times(1)).save(Mockito.<Customer>any());
    }

    @Test
    public void createCustomer_shouldThrowException_whenSaveFails() {
        UUID countryId = UUID.randomUUID();
        CreateCustomerCommand command = new CreateCustomerCommand(
                "Name", "Surname", "name@example.com", "@username", countryId
        );

        Country country = new Country();
        country.setCountryId(countryId);
        when(countryRepository.findById(countryId)).thenReturn(Optional.of(country));

        when(customerRepository.save(Mockito.<Customer>any())).thenThrow(new RuntimeException("Save failed"));

        assertThrows(RuntimeException.class, () -> customerServiceImpl.createCustomer(command));

        verify(countryRepository, times(1)).findById(countryId);
        verify(customerRepository, times(1)).save(Mockito.<Customer>any());
    }

}