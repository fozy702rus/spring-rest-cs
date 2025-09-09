package ru.fozydev.customerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fozydev.customerservice.dto.operations.CustomerResponseDTO;
import ru.fozydev.customerservice.model.Customer;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query(
            "SELECT c FROM Customer c " +
                    "WHERE ((:fullName IS NULL) " +
                    "OR (LOWER(CONCAT(c.name, ' ', c.surname)) LIKE LOWER(CONCAT('%', :fullName, '%')))) " +
                    "AND (:countryId IS NULL OR c.country.countryId = :countryId) "
    )
    Page<Customer> searchCustomers(
            @Param("fullName") String fullName,
            @Param("countryId") UUID countryId,
            Pageable pageable
    );
}
// Поиск клиентов с фильтром по имени например ("Ivan" -> будет искать "Ivan" в имени и фамилии) также по стране по UUID если параметры null фильтр не применяется