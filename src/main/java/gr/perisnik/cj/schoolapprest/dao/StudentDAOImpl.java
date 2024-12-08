package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Student;
import gr.perisnik.cj.schoolapprest.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import javax.ws.rs.ext.Provider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IStudentDAO interface for Student entities.
 */
@Provider
public class StudentDAOImpl implements IStudentDAO {

    @Override
    public List<Student> getAllStudents() throws DataAccessException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setDayOfBirth(rs.getString("day_of_birth"));
                student.setPhone(rs.getString("phone"));

                students.add(student);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving all students.", e);
        }
        return students;
    }

    @Override
    public Student getStudentById(int id) throws StudentNotFoundException, DataAccessException {
        Student student = null;
        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setEmail(rs.getString("email"));
                student.setDayOfBirth(rs.getString("day_of_birth"));
                student.setPhone(rs.getString("phone"));
            }

            if (student == null) {
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving student with ID " + id, e);
        }
        return student;
    }

    @Override
    public Student addStudent(Student student) throws DataAccessException {
        String sql = "INSERT INTO students (first_name, last_name, email, day_of_birth, phone) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDayOfBirth());
            stmt.setString(5, student.getPhone());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to insert new student.", null);
            }

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                student.setId(generatedKeys.getInt(1));
            } else {
                throw new DataAccessException("Failed to retrieve generated ID for new student.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error inserting new student.", e);
        }

        return student;
    }

    @Override
    public void updateStudent(Student student) throws StudentNotFoundException, DataAccessException {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, email = ?, day_of_birth = ?, " +
                "phone = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getDayOfBirth());
            stmt.setString(5, student.getPhone());
            stmt.setInt(6, student.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new StudentNotFoundException("Student with ID " + student.getId() + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error updating student with ID " + student.getId(), e);
        }
    }

    @Override
    public void deleteStudent(int id) throws StudentNotFoundException, DataAccessException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new StudentNotFoundException("Student with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error deleting student with ID " + id, e);
        }
    }
}