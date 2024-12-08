package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.CourseDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.CourseServiceException;

import java.util.List;

/**
 * Interface for Course Service.
 * Defines operations for Course entities.
 */
public interface ICourseService {

    /**
     * Adds a new course.
     *
     * @param courseDTO The course data transfer object containing the course details.
     * @return The created CourseDTO.
     * @throws CourseServiceException if there is an error during course creation.
     */
    CourseDTO addCourse(CourseDTO courseDTO) throws CourseServiceException;

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return The CourseDTO of the requested course.
     * @throws CourseServiceException if the course is not found or there is an error.
     */
    CourseDTO getCourseById(int id) throws CourseServiceException;

    /**
     * Retrieves all courses.
     *
     * @return A list of all CourseDTOs.
     * @throws CourseServiceException if there is an error retrieving the courses.
     */
    List<CourseDTO> getAllCourses() throws CourseServiceException;

    /**
     * Updates the details of an existing course.
     *
     * @param id The ID of the course to update.
     * @param courseDTO The updated course details.
     * @return The updated CourseDTO.
     * @throws CourseServiceException if there is an error during the update.
     */
    CourseDTO updateCourse(int id, CourseDTO courseDTO) throws CourseServiceException;

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to delete.
     * @throws CourseServiceException if there is an error during the deletion.
     */
    void deleteCourse(int id) throws CourseServiceException;
}