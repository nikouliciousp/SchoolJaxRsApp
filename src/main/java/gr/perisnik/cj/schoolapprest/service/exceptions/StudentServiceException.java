package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to StudentService operations.
 */
public class StudentServiceException extends RuntimeException {
    public StudentServiceException(String message) {
        super(message);
    }

    public StudentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}