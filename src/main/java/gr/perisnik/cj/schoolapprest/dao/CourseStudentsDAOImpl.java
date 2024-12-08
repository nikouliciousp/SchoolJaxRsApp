package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.CourseStudents;
import gr.perisnik.cj.schoolapprest.dao.exceptions.CourseStudentsNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ICourseStudentsDAO interface for managing Course-Student enrollments.
 */
public class CourseStudentsDAOImpl implements ICourseStudentsDAO {

    @Override
    public List<CourseStudents> getAllCourseStudents() throws DataAccessException {
        List<CourseStudents> courseStudentsList = new ArrayList<>();
        String sql = "SELECT * FROM course_students";  // Assuming the table name is 'course_students'

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CourseStudents courseStudents = new CourseStudents();
                courseStudents.setCourseId(rs.getInt("course_id"));
                courseStudents.setStudentId(rs.getInt("student_id"));
                courseStudents.setEnrollmentDate(rs.getString("enrollment_date"));
                courseStudentsList.add(courseStudents);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving course-student enrollments.", e);
        }
        return courseStudentsList;
    }

    @Override
    public List<CourseStudents> getStudentsByCourseId(int courseId) throws DataAccessException {
        List<CourseStudents> courseStudentsList = new ArrayList<>();
        String sql = "SELECT * FROM course_students WHERE course_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CourseStudents courseStudents = new CourseStudents();
                    courseStudents.setCourseId(rs.getInt("course_id"));
                    courseStudents.setStudentId(rs.getInt("student_id"));
                    courseStudents.setEnrollmentDate(rs.getString("enrollment_date"));
                    courseStudentsList.add(courseStudents);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving students by course ID.", e);
        }
        return courseStudentsList;
    }

    @Override
    public List<CourseStudents> getCoursesByStudentId(int studentId) throws DataAccessException {
        List<CourseStudents> courseStudentsList = new ArrayList<>();
        String sql = "SELECT * FROM course_students WHERE student_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CourseStudents courseStudents = new CourseStudents();
                    courseStudents.setCourseId(rs.getInt("course_id"));
                    courseStudents.setStudentId(rs.getInt("student_id"));
                    courseStudents.setEnrollmentDate(rs.getString("enrollment_date"));
                    courseStudentsList.add(courseStudents);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving courses by student ID.", e);
        }
        return courseStudentsList;
    }

    @Override
    public CourseStudents addEnrollment(CourseStudents courseStudents) throws DataAccessException {
        String sql = "INSERT INTO course_students (course_id, student_id, enrollment_date) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, courseStudents.getCourseId());
            ps.setInt(2, courseStudents.getStudentId());
            ps.setString(3, courseStudents.getEnrollmentDate());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Error inserting course-student enrollment.", null);
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    courseStudents.setCourseId(rs.getInt(1));  // Setting the generated course_id
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error enrolling student in the course.", e);
        }
        return courseStudents;
    }

    @Override
    public void removeEnrollment(int courseId, int studentId) throws CourseStudentsNotFoundException, DataAccessException {
        String sql = "DELETE FROM course_students WHERE course_id = ? AND student_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, courseId);
            ps.setInt(2, studentId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new CourseStudentsNotFoundException("Enrollment between course ID " + courseId + " and student ID " + studentId + " not found.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error removing student enrollment.", e);
        }
    }
}