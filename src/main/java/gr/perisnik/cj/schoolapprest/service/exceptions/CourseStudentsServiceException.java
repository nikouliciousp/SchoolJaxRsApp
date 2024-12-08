package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to Course-Students service operations.
 */
public class CourseStudentsServiceException extends RuntimeException {
    public CourseStudentsServiceException(String message) {
        super(message);
    }

    public CourseStudentsServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}