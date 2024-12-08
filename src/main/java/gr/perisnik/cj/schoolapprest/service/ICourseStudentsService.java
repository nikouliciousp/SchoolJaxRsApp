package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.CourseStudentsDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.CourseStudentsServiceException;

import java.util.List;

/**
 * Service interface for Course-Student relationship operations.
 */
public interface ICourseStudentsService {

    /**
     * Retrieves all course-student relationships.
     *
     * @return a list of course-student relationships.
     * @throws CourseStudentsServiceException if an error occurs during retrieval.
     */
    List<CourseStudentsDTO> getAllCourseStudents() throws CourseStudentsServiceException;

    /**
     * Retrieves a course-student relationship by its course and student IDs.
     *
     * @param courseId the ID of the course.
     * @param studentId the ID of the student.
     * @return the course-student relationship.
     * @throws CourseStudentsServiceException if an error occurs during retrieval.
     */
    CourseStudentsDTO getCourseStudentByIds(int courseId, int studentId) throws CourseStudentsServiceException;

    /**
     * Adds a new course-student relationship.
     *
     * @param courseStudentsDTO the relationship to add.
     * @return the added course-student relationship.
     * @throws CourseStudentsServiceException if an error occurs during adding.
     */
    CourseStudentsDTO addCourseStudent(CourseStudentsDTO courseStudentsDTO) throws CourseStudentsServiceException;

    /**
     * Deletes a course-student relationship by its course and student IDs.
     *
     * @param courseId the ID of the course.
     * @param studentId the ID of the student.
     * @throws CourseStudentsServiceException if an error occurs during deletion.
     */
    void deleteCourseStudent(int courseId, int studentId) throws CourseStudentsServiceException;
}