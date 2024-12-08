package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a CourseStudents is not found.
 */
public class CourseStudentsNotFoundException extends RuntimeException {

    public CourseStudentsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
