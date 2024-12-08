package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a TeacherDepartment association is not found.
 */
public class TeacherDepartmentNotFoundException extends RuntimeException {

    /**
     * Constructs a new TeacherDepartmentNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public TeacherDepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}