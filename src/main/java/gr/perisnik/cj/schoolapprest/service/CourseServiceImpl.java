package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.ICourseDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.CourseDTO;
import gr.perisnik.cj.schoolapprest.model.Course;
import gr.perisnik.cj.schoolapprest.service.exceptions.CourseServiceException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class CourseServiceImpl implements ICourseService {

    @Inject
    private ICourseDAO courseDAO; // Injecting the CourseDAO for database operations

    /**
     * Adds a new course.
     *
     * @param courseDTO The course data transfer object containing the course details.
     * @return The created CourseDTO.
     * @throws CourseServiceException if there is an error during the course creation.
     */
    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) throws CourseServiceException {
        try {
            // Create a new Course model object and set its properties
            Course course = new Course();
            course.setName(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            course.setCredits(courseDTO.getCredits());
            course.setTeacherId(courseDTO.getTeacherId());

            // Add the course to the database
            course = courseDAO.addCourse(course);

            // Return the created course as a DTO by calling the private method
            return convertToDTO(course);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error adding course.", e);
        }
    }

    /**
     * Retrieves a course by its ID.
     *
     * @param id The ID of the course to retrieve.
     * @return The CourseDTO of the requested course.
     * @throws CourseServiceException if the course is not found or there is an error.
     */
    @Override
    public CourseDTO getCourseById(int id) throws CourseServiceException {
        try {
            Course course = courseDAO.getCourseById(id);
            if (course == null) {
                throw new CourseServiceException("Course with ID " + id + " not found.");
            }
            return convertToDTO(course);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error retrieving course with ID " + id, e);
        }
    }

    /**
     * Retrieves all courses.
     *
     * @return A list of all CourseDTOs.
     * @throws CourseServiceException if there is an error retrieving the courses.
     */
    @Override
    public List<CourseDTO> getAllCourses() throws CourseServiceException {
        try {
            List<Course> courses = courseDAO.getAllCourses();
            List<CourseDTO> courseDTOs = new ArrayList<>();
            for (Course course : courses) {
                courseDTOs.add(convertToDTO(course));
            }
            return courseDTOs;
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error retrieving all courses.", e);
        }
    }

    /**
     * Updates the details of an existing course.
     *
     * @param id The ID of the course to update.
     * @param courseDTO The updated course details.
     * @return The updated CourseDTO.
     * @throws CourseServiceException if there is an error during the update.
     */
    @Override
    public CourseDTO updateCourse(int id, CourseDTO courseDTO) throws CourseServiceException {
        try {
            // Create a new Course model object with updated data
            Course course = new Course();
            course.setId(id);
            course.setName(courseDTO.getName());
            course.setDescription(courseDTO.getDescription());
            course.setCredits(courseDTO.getCredits());
            course.setTeacherId(courseDTO.getTeacherId());

            // Update the course in the database
            courseDAO.updateCourse(course);

            // Return the updated course as a DTO by calling the private method
            return convertToDTO(course);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error updating course with ID " + id, e);
        }
    }

    /**
     * Deletes a course by its ID.
     *
     * @param id The ID of the course to delete.
     * @throws CourseServiceException if there is an error during the deletion.
     */
    @Override
    public void deleteCourse(int id) throws CourseServiceException {
        try {
            courseDAO.deleteCourse(id);
        } catch (DataAccessException e) {
            throw new CourseServiceException("Error deleting course with ID " + id, e);
        }
    }

    /**
     * Private method to convert a Course model to a CourseDTO.
     *
     * @param course The course model object to convert.
     * @return The corresponding CourseDTO.
     */
    private CourseDTO convertToDTO(Course course) {
        if (course == null) {
            return null;
        }
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setId(course.getId());
        courseDTO.setName(course.getName());
        courseDTO.setDescription(course.getDescription());
        courseDTO.setCredits(course.getCredits());
        courseDTO.setTeacherId(course.getTeacherId());
        return courseDTO;
    }
}