package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a Teacher is not found.
 */
public class TeacherNotFoundException extends RuntimeException {
    public TeacherNotFoundException(String message) {
        super(message);
    }
}