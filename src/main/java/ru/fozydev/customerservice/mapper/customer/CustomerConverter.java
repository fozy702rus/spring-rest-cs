package ru.fozydev.customerservice.mapper.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fozydev.customerservice.dto.CustomerResponseDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;
import ru.fozydev.customerservice.model.Customer;

import java.util.List;

@Component
public class CustomerConverter {

    private final CustomerDtoMapper customerDtoMapper;

    @Autowired
    public CustomerConverter(CustomerDtoMapper customerDtoMapper) {
        this.customerDtoMapper = customerDtoMapper;
    }

    public CustomerDTO toDto(Customer customer) {
        return customerDtoMapper.toDto(customer);
    }

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        return customerDtoMapper.toResponseDTO(customer);
    }

    public List<CustomerResponseDTO> toResponseDTOList(List<Customer> customers) {
        return customerDtoMapper.toResponseDTOList(customers);
    }
}