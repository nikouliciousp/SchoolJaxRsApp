package gr.perisnik.cj.schoolapprest.dao.exceptions;

/**
 * General exception for data access errors.
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}