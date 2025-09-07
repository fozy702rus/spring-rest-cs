package ru.fozydev.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fozydev.customerservice.model.ContactDetails;

import java.util.UUID;

public interface ContactDetailsRepository extends JpaRepository<ContactDetails, UUID> {

}