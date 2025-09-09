package ru.fozydev.customerservice.dto.contactdetails;

public class ContactDetailsDTO {

    private String telegramId;
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
