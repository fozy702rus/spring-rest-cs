package ru.fozydev.customerservice.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fozydev.customerservice.dto.operations.CustomerCreateRequest;
import ru.fozydev.customerservice.dto.operations.CustomerResponseDTO;
import ru.fozydev.customerservice.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "country", ignore = true)
    @Mapping(target = "contactDetails", ignore = true)
    @Mapping(source = "customerDTO.name", target = "name")
    @Mapping(source = "customerDTO.surname", target = "surname")
    Customer toCustomer(CustomerCreateRequest dto);

    @Mapping(source = "country.countryId", target = "countryId")
    @Mapping(source = "contactDetails.email", target = "email")
    @Mapping(source = "contactDetails.telegramId", target = "telegramId")
    CustomerResponseDTO toResponse(Customer customer);

    List<CustomerResponseDTO> toResponseList(List<Customer> customers);
}
