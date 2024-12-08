package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a Course is not found.
 */
public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}