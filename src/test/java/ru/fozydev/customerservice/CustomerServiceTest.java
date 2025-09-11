package ru.fozydev.customerservice;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.fozydev.customerservice.repository.CountryRepository;
import ru.fozydev.customerservice.repository.CustomerRepository;
import ru.fozydev.customerservice.service.customer.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class) // подключаем мокито
public class CustomerServiceTest {

        @Mock
        private CustomerRepository customerRepository;

        @Mock
        private CountryRepository countryRepository;

        @InjectMocks
        private CustomerServiceImpl customerServiceImpl;

}