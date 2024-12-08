package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a Student is not found.
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}