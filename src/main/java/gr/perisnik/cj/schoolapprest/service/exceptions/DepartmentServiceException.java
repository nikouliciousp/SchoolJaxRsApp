package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to Department service operations.
 */
public class DepartmentServiceException extends RuntimeException {
    public DepartmentServiceException(String message) {
        super(message);
    }

    public DepartmentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}