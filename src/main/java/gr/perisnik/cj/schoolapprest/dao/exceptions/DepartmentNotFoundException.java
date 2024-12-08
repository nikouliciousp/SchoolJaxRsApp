package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * Exception thrown when a Department is not found.
 */
public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
