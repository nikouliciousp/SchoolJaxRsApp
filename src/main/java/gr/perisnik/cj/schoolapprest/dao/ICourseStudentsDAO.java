package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.CourseStudents;
import gr.perisnik.cj.schoolapprest.dao.exceptions.CourseStudentsNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Course-Student Data Access Object.
 * Defines CRUD operations for the relationship between Course and Student entities.
 */
public interface ICourseStudentsDAO {

    /**
     * Retrieves all enrollments (Course-Student relationships) from the database.
     *
     * @return List of CourseStudents objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<CourseStudents> getAllCourseStudents() throws DataAccessException;

    /**
     * Retrieves all students enrolled in a specific course.
     *
     * @param courseId The ID of the course
     * @return List of CourseStudents objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<CourseStudents> getStudentsByCourseId(int courseId) throws DataAccessException;

    /**
     * Retrieves all courses that a specific student is enrolled in.
     *
     * @param studentId The ID of the student
     * @return List of CourseStudents objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<CourseStudents> getCoursesByStudentId(int studentId) throws DataAccessException;

    /**
     * Adds a student to a course (enrolls the student in the course).
     *
     * @param courseStudents The CourseStudents object containing course and student information
     * @return The enrolled CourseStudents object with generated enrollment date
     * @throws DataAccessException If an error occurs while inserting the enrollment data
     */
    CourseStudents addEnrollment(CourseStudents courseStudents) throws DataAccessException;

    /**
     * Removes a student from a course (unenrolls the student from the course).
     *
     * @param courseId The ID of the course
     * @param studentId The ID of the student
     * @throws CourseStudentsNotFoundException If the enrollment between the course and student does not exist
     * @throws DataAccessException If an error occurs while deleting the enrollment
     */
    void removeEnrollment(int courseId, int studentId) throws CourseStudentsNotFoundException, DataAccessException;
}