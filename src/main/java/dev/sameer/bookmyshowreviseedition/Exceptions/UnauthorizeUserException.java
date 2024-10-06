package dev.sameer.bookmyshowreviseedition.Exceptions;

public class UnauthorizeUserException extends RuntimeException {
    public UnauthorizeUserException(String message) {
        super(message);
    }
}
