package com.queueflow.domain.exception;

public class ServiceDeskAlreadyExistsException
        extends RuntimeException {

    public ServiceDeskAlreadyExistsException(String code) {
        super("Un service avec le code '%s' existe déjà"
                .formatted(code));
    }
}