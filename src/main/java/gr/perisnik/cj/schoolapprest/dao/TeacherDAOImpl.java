package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Teacher;
import gr.perisnik.cj.schoolapprest.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import javax.ws.rs.ext.Provider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITeacherDAO interface for Teacher entities.
 */
@Provider
public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public List<Teacher> getAllTeachers() throws DataAccessException {
        List<Teacher> teachers = new ArrayList<>();
        String sql = "SELECT * FROM teachers";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setDayOfBirth(rs.getString("day_of_birth"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setDepartment(rs.getInt("department_id"));

                teachers.add(teacher);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving all teachers.", e);
        }
        return teachers;
    }

    @Override
    public Teacher getTeacherById(int id) throws TeacherNotFoundException, DataAccessException {
        Teacher teacher = null;
        String sql = "SELECT * FROM teachers WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setDayOfBirth(rs.getString("day_of_birth"));
                teacher.setPhone(rs.getString("phone"));
                teacher.setDepartment(rs.getInt("department_id"));
            }

            if (teacher == null) {
                throw new TeacherNotFoundException("Teacher with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving teacher with ID " + id, e);
        }
        return teacher;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) throws DataAccessException {
        String sql = "INSERT INTO teachers (first_name, last_name, email, day_of_birth, phone) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setString(4, teacher.getDayOfBirth());
            stmt.setString(5, teacher.getPhone());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to insert new teacher.", null);
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                teacher.setId(generatedKeys.getInt(1));
            } else {
                throw new DataAccessException("Failed to retrieve generated ID for new teacher.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error inserting new teacher.", e);
        }

        return teacher;
    }

    @Override
    public void updateTeacher(Teacher teacher) throws TeacherNotFoundException, DataAccessException {
        String sql = "UPDATE teachers SET first_name = ?, last_name = ?, email = ?, day_of_birth = ?, " +
                "phone = ?, department_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, teacher.getFirstName());
            stmt.setString(2, teacher.getLastName());
            stmt.setString(3, teacher.getEmail());
            stmt.setString(4, teacher.getDayOfBirth());
            stmt.setString(5, teacher.getPhone());
            stmt.setInt(6, teacher.getDepartment());
            stmt.setInt(7, teacher.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new TeacherNotFoundException("Teacher with ID " + teacher.getId() + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error updating teacher with ID " + teacher.getId(), e);
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherNotFoundException, DataAccessException {
        String sql = "DELETE FROM teachers WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new TeacherNotFoundException("Teacher with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error deleting teacher with ID " + id, e);
        }
    }
}