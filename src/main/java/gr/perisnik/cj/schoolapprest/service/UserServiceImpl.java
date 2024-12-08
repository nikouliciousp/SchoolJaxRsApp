package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.IUserDAO;
import gr.perisnik.cj.schoolapprest.model.User;
import gr.perisnik.cj.schoolapprest.dao.exceptions.UserNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.service.exceptions.UserServiceException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.List;

/**
 * Implementation of IUserService interface for User entities.
 */
@Provider
public class UserServiceImpl implements IUserService {

    @Inject
    private IUserDAO userDAO;

    @Override
    public User createUser(User user) throws UserServiceException {
        try {
            return userDAO.createUser(user);
        } catch (DataAccessException e) {
            throw new UserServiceException("Error creating user: " + user, e);
        }
    }

    @Override
    public User getUserById(int id) throws UserServiceException {
        try {
            return userDAO.getUserById(id);
        } catch (UserNotFoundException e) {
            throw new UserServiceException("User not found with ID " + id, e);
        } catch (DataAccessException e) {
            throw new UserServiceException("Error retrieving user with ID " + id, e);
        }
    }

    @Override
    public List<User> getAllUsers() throws UserServiceException {
        try {
            return userDAO.getAllUsers();
        } catch (DataAccessException e) {
            throw new UserServiceException("Error retrieving all users", e);
        }
    }

    @Override
    public User updateUser(User user) throws UserServiceException {
        try {
            return userDAO.updateUser(user);
        } catch (UserNotFoundException e) {
            throw new UserServiceException("User not found: " + user, e);
        } catch (DataAccessException e) {
            throw new UserServiceException("Error updating user: " + user, e);
        }
    }

    @Override
    public boolean deleteUser(int id) throws UserServiceException {
        try {
            return userDAO.deleteUser(id);
        } catch (UserNotFoundException e) {
            throw new UserServiceException("User not found with ID " + id, e);
        } catch (DataAccessException e) {
            throw new UserServiceException("Error deleting user with ID " + id, e);
        }
    }
}