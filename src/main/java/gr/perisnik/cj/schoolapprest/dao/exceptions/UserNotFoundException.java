package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a User is not found.
 */
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}