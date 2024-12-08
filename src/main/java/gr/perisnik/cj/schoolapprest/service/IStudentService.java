package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.StudentDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.StudentServiceException;

import java.util.List;

/**
 * Interface for Student Service.
 * Defines operations for Student entities.
 */
public interface IStudentService {

    /**
     * Adds a new student.
     *
     * @param studentDTO The student data transfer object containing the student details.
     * @return The created StudentDTO.
     * @throws StudentServiceException if there is an error during the student creation.
     */
    StudentDTO addStudent(StudentDTO studentDTO) throws StudentServiceException;

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return The StudentDTO of the requested student.
     * @throws StudentServiceException if the student is not found or there is an error.
     */
    StudentDTO getStudentById(int id) throws StudentServiceException;

    /**
     * Retrieves all students.
     *
     * @return A list of all StudentDTOs.
     * @throws StudentServiceException if there is an error retrieving the students.
     */
    List<StudentDTO> getAllStudents() throws StudentServiceException;

    /**
     * Updates the details of an existing student.
     *
     * @param id The ID of the student to update.
     * @param studentDTO The updated student details.
     * @return The updated StudentDTO.
     * @throws StudentServiceException if there is an error during the update.
     */
    StudentDTO updateStudent(int id, StudentDTO studentDTO) throws StudentServiceException;

    /**
     * Deletes a student by their ID.
     *
     * @param id The ID of the student to delete.
     * @throws StudentServiceException if there is an error during the deletion.
     */
    void deleteStudent(int id) throws StudentServiceException;
}