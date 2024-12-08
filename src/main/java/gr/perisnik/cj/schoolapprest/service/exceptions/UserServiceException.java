package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to UserService operations.
 */
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }

    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}