package ru.fozydev.customerservice.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entity, Object id) {
        super(entity + " with id " + id + " not found");
    }
}