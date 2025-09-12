package ru.fozydev.customerservice.mapper.customer;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.fozydev.customerservice.dto.customer.CustomerResponseDTO;
import ru.fozydev.customerservice.model.Customer;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "surname", target = "surname")
    @Mapping(source = "contactDetails.email", target = "email")
    @Mapping(source = "contactDetails.telegramId", target = "telegramId")
    @Mapping(source = "country.name", target = "countryName")
    @Mapping(source = "country.code", target = "countryCode")
    CustomerResponseDTO toResponseDTO(Customer customer);

    List<CustomerResponseDTO> toResponseDTOList(List<Customer> customers);
}