package org.tmforum.projects.core.exceptions;

public class UnknownResourceException extends FunctionalException {
    
    public UnknownResourceException(ExceptionType type) {
        super(type);
    }
    
    public UnknownResourceException(ExceptionType type, String message) {
        super(type, message);
    }     
    
}
