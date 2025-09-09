package ru.fozydev.customerservice.dto.contactdetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactDetailsDTO {

    @Pattern(regexp = "^@([A-Za-z0-9_]{5,32})$", message = "@nickname")
    private String telegramId;

    @Email(message = "e@mail.ru/com формат")
    @NotBlank(message = "поле email не может быть пустым")
    private String email;

    public String getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}