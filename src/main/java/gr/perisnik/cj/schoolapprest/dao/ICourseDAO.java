package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Course;
import gr.perisnik.cj.schoolapprest.dao.exceptions.CourseNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Course Data Access Object.
 * Defines CRUD operations for Course entities.
 */
public interface ICourseDAO {

    /**
     * Retrieves all courses.
     *
     * @return a list of all courses.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    List<Course> getAllCourses() throws DataAccessException;

    /**
     * Retrieves a course by its ID.
     *
     * @param id the ID of the course.
     * @return the course with the specified ID.
     * @throws CourseNotFoundException if the course is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    Course getCourseById(int id) throws CourseNotFoundException, DataAccessException;

    /**
     * Adds a new course.
     *
     * @param course the course to add.
     * @return the added course with its generated ID.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    Course addCourse(Course course) throws DataAccessException;

    /**
     * Updates an existing course.
     *
     * @param course the course to update.
     * @throws CourseNotFoundException if the course to update is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    void updateCourse(Course course) throws CourseNotFoundException, DataAccessException;

    /**
     * Deletes a course by its ID.
     *
     * @param id the ID of the course to delete.
     * @throws CourseNotFoundException if the course to delete is not found.
     * @throws DataAccessException     if there is an issue accessing the data.
     */
    void deleteCourse(int id) throws CourseNotFoundException, DataAccessException;
}