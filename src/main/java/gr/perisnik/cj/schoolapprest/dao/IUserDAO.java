package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.User;
import gr.perisnik.cj.schoolapprest.dao.exceptions.UserNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for User Data Access Object.
 * Defines CRUD operations for User entities.
 */
public interface IUserDAO {

    /**
     * Create a new user.
     *
     * @param user the User entity to create
     * @return the created User entity
     * @throws DataAccessException if there is an error accessing the data
     */
    User createUser(User user) throws DataAccessException;

    /**
     * Retrieve a user by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User entity
     * @throws UserNotFoundException if the user is not found
     * @throws DataAccessException   if there is an error accessing the data
     */
    User getUserById(int id) throws UserNotFoundException, DataAccessException;

    /**
     * Retrieve all users.
     *
     * @return a list of all User entities
     * @throws DataAccessException if there is an error accessing the data
     */
    List<User> getAllUsers() throws DataAccessException;

    /**
     * Update an existing user.
     *
     * @param user the User entity with updated information
     * @return the updated User entity
     * @throws UserNotFoundException if the user is not found
     * @throws DataAccessException   if there is an error accessing the data
     */
    User updateUser(User user) throws UserNotFoundException, DataAccessException;

    /**
     * Delete a user by its ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false otherwise
     * @throws UserNotFoundException if the user is not found
     * @throws DataAccessException   if there is an error accessing the data
     */
    boolean deleteUser(int id) throws UserNotFoundException, DataAccessException;
}