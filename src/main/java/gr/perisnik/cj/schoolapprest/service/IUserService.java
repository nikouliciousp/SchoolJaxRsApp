package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.model.User;
import gr.perisnik.cj.schoolapprest.service.exceptions.UserServiceException;

import java.util.List;

/**
 * Interface for User Service.
 * Defines operations for User entities.
 */
public interface IUserService {

    /**
     * Create a new user.
     *
     * @param user the User entity to create
     * @return the created User entity
     * @throws UserServiceException if there is an error during the creation process
     */
    User createUser(User user) throws UserServiceException;

    /**
     * Retrieve a user by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the User entity
     * @throws UserServiceException if the user is not found or if there is an error during the retrieval process
     */
    User getUserById(int id) throws UserServiceException;

    /**
     * Retrieve all users.
     *
     * @return a list of all User entities
     * @throws UserServiceException if there is an error during the retrieval process
     */
    List<User> getAllUsers() throws UserServiceException;

    /**
     * Update an existing user.
     *
     * @param user the User entity with updated information
     * @return the updated User entity
     * @throws UserServiceException if the user is not found or if there is an error during the update process
     */
    User updateUser(User user) throws UserServiceException;

    /**
     * Delete a user by its ID.
     *
     * @param id the ID of the user to delete
     * @return true if the user was deleted, false otherwise
     * @throws UserServiceException if the user is not found or if there is an error during the deletion process
     */
    boolean deleteUser(int id) throws UserServiceException;
}