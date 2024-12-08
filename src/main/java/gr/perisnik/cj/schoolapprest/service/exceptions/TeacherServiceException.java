package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to TeacherService operations.
 */
public class TeacherServiceException extends RuntimeException {
    public TeacherServiceException(String message) {
        super(message);
    }

    public TeacherServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}