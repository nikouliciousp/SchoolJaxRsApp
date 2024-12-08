package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Course;
import gr.perisnik.cj.schoolapprest.dao.exceptions.CourseNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import javax.ws.rs.ext.Provider;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICourseDAO interface for Course entities.
 */
@Provider
public class CourseDAOImpl implements ICourseDAO {

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    @Override
    public List<Course> getAllCourses() throws DataAccessException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setCredits(rs.getInt("credits"));
                course.setTeacherId(rs.getInt("teacher_id"));
                courses.add(course);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving courses.", e);
        }
        return courses;
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course.
     * @return the course with the specified ID.
     * @throws CourseNotFoundException if the course is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    @Override
    public Course getCourseById(int id) throws CourseNotFoundException, DataAccessException {
        String sql = "SELECT * FROM courses WHERE id = ?";
        Course course = null;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDescription(rs.getString("description"));
                course.setCredits(rs.getInt("credits"));
                course.setTeacherId(rs.getInt("teacher_id"));
            }

            if (course == null) {
                throw new CourseNotFoundException("Course with ID " + id + " not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving course by ID.", e);
        }
        return course;
    }

    /**
     * Adds a new course.
     *
     * @param course the course to add.
     * @return the added course with its generated ID.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    @Override
    public Course addCourse(Course course) throws DataAccessException {
        String sql = "INSERT INTO courses (name, description, credits) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getCredits());

            int rowsAffected = stmt.executeUpdate();

            // Ensure the insertion affected at least one row, else throw an exception
            if (rowsAffected == 0) {
                throw new DataAccessException("Failed to insert new course.", null); // No underlying cause here
            }

            // Get the generated ID for the new course
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                course.setId(generatedKeys.getInt(1));
            } else {
                throw new DataAccessException("Failed to retrieve generated ID for new course.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            // Pass the exception as the cause to DataAccessException
            throw new DataAccessException("Error inserting new course.", e);
        }
        return course;
    }

    /**
     * Updates an existing course.
     *
     * @param course the course to update.
     * @throws CourseNotFoundException if the course to update is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    @Override
    public void updateCourse(Course course) throws CourseNotFoundException, DataAccessException {
        String sql = "UPDATE courses SET name = ?, description = ?, credits = ?, teacher_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getName());
            stmt.setString(2, course.getDescription());
            stmt.setInt(3, course.getCredits());
            stmt.setInt(4, course.getTeacherId());
            stmt.setInt(5, course.getId());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new CourseNotFoundException("Course with ID " + course.getId() + " not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error updating course.", e);
        }
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete.
     * @throws CourseNotFoundException if the course to delete is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    @Override
    public void deleteCourse(int id) throws CourseNotFoundException, DataAccessException {
        String sql = "DELETE FROM courses WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new CourseNotFoundException("Course with ID " + id + " not found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error deleting course.", e);
        }
    }
}