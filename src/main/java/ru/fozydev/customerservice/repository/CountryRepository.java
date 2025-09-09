package ru.fozydev.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fozydev.customerservice.model.Country;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

}