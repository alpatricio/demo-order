package com.apatricio.demo.exception;

public class ServiceException extends RuntimeException
{
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
