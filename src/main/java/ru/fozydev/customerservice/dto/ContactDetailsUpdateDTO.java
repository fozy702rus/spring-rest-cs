package ru.fozydev.customerservice.dto;

import java.util.UUID;

public class ContactDetailsUpdateDTO {

    private UUID contactDetailsDtoId;
    private String email;
    private String telegramId;

    public UUID getContactDetailsDtoId() {
        return contactDetailsDtoId;
    }

    public void setContactDetailsDtoId(UUID contactDetailsDtoId) {
        this.contactDetailsDtoId = contactDetailsDtoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }
}