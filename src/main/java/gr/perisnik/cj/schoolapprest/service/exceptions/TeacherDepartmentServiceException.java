package gr.perisnik.cj.schoolapprest.service.exceptions;

/**
 * Custom exception for errors related to Teacher-Department service operations.
 */
public class TeacherDepartmentServiceException extends RuntimeException {

    /**
     * Constructor with message only.
     *
     * @param message Error message describing the exception
     */
    public TeacherDepartmentServiceException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
     *
     * @param message Error message describing the exception
     * @param cause   The throwable cause of the exception
     */
    public TeacherDepartmentServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}