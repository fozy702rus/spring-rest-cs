package ru.fozydev.customerservice.mapper.customer;

import org.springframework.stereotype.Component;
import ru.fozydev.customerservice.command.CreateCustomerCommand;
import ru.fozydev.customerservice.command.UpdateCustomerCommand;
import ru.fozydev.customerservice.dto.contactdetails.ContactDetailsDTO;
import ru.fozydev.customerservice.dto.country.CountryDTO;
import ru.fozydev.customerservice.dto.customer.CustomerDTO;
import ru.fozydev.customerservice.dto.customer.CustomerRequestDTO;

import java.util.Optional;
import java.util.UUID;

@Component
public class CustomerCommandMapper {

    public CreateCustomerCommand buildCreateCommand(CustomerRequestDTO dto) {
        return new CreateCustomerCommand(
                safeGetName(dto.getCustomerDTO()),
                safeGetSurname(dto.getCustomerDTO()),
                safeGetEmail(dto.getContactDetailsDTO()),
                safeGetTelegramId(dto.getContactDetailsDTO()),
                safeGetCountryId(dto.getCountryDTO())
        );
    }

    public UpdateCustomerCommand buildUpdateCommand(CustomerRequestDTO dto) {
        return new UpdateCustomerCommand(
                safeGetName(dto.getCustomerDTO()),
                safeGetSurname(dto.getCustomerDTO()),
                safeGetEmail(dto.getContactDetailsDTO()),
                safeGetTelegramId(dto.getContactDetailsDTO()),
                safeGetCountryId(dto.getCountryDTO())
        );
    }

    private String safeGetName(CustomerDTO customerDTO) {
        return Optional.ofNullable(customerDTO).map(CustomerDTO::getName).orElse(null);
    }

    private String safeGetSurname(CustomerDTO customerDTO) {
        return Optional.ofNullable(customerDTO).map(CustomerDTO::getSurname).orElse(null);
    }

    private String safeGetEmail(ContactDetailsDTO contactDTO) {
        return Optional.ofNullable(contactDTO).map(ContactDetailsDTO::getEmail).orElse(null);
    }

    private String safeGetTelegramId(ContactDetailsDTO contactDTO) {
        return Optional.ofNullable(contactDTO).map(ContactDetailsDTO::getTelegramId).orElse(null);
    }

    private UUID safeGetCountryId(CountryDTO countryDTO) {
        return Optional.ofNullable(countryDTO).map(CountryDTO::getCountryId).orElse(null);
    }
}
