package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to CourseService operations.
 */
public class CourseServiceException extends RuntimeException {
    public CourseServiceException(String message) {
        super(message);
    }

    public CourseServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}