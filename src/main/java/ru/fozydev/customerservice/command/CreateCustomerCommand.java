package ru.fozydev.customerservice.command;

import java.util.UUID;

public record CreateCustomerCommand(
        String name,
        String surname,
        String email,
        String telegramId,
        UUID countryId
) {
}
