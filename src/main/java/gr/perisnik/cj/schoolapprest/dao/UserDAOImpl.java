package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.User;
import gr.perisnik.cj.schoolapprest.dao.exceptions.UserNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import javax.ws.rs.ext.Provider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IUserDAO interface for User entities.
 */
@Provider
public class UserDAOImpl implements IUserDAO {

    @Override
    public User createUser(User user) throws DataAccessException {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error creating user", e);
        }
        return user;
    }

    @Override
    public User getUserById(int id) throws UserNotFoundException, DataAccessException {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                } else {
                    throw new UserNotFoundException("User not found with id: " + id);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving user", e);
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws DataAccessException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                users.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving all users", e);
        }
        return users;
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException, DataAccessException {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getId());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new UserNotFoundException("User not found with id: " + user.getId());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error updating user", e);
        }
        return user;
    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException, DataAccessException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new UserNotFoundException("User not found with id: " + id);
            }
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error deleting user", e);
        }
    }
}