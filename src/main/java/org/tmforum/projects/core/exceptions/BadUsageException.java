package org.tmforum.projects.core.exceptions;


public class BadUsageException extends FunctionalException {
    
    public BadUsageException(ExceptionType type) {
        super(type);
    }
    
    public BadUsageException(ExceptionType type, String message) {
        super(type, message);
    }     
    
}

