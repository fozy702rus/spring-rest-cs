package ru.fozydev.customerservice.util;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fozydev.customerservice.exceptions.EntityNotFoundException;

public class RepositoryUtils {

    private RepositoryUtils() {}

    public static <T, ID> T findByIdOrThrow(JpaRepository<T, ID> repository, ID id, String entity) {
        return repository.findById(id).
                orElseThrow(() -> new EntityNotFoundException(entity, id));
    }
}
