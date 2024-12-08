package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.TeacherDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.TeacherServiceException;

import java.util.List;

/**
 * Interface for Teacher Service.
 * Defines operations for Teacher entities.
 */
public interface ITeacherService {

    /**
     * Adds a new teacher.
     *
     * @param teacherDTO The teacher data transfer object containing the teacher details.
     * @return The created TeacherDTO.
     * @throws TeacherServiceException if there is an error during teacher creation.
     */
    TeacherDTO addTeacher(TeacherDTO teacherDTO) throws TeacherServiceException;

    /**
     * Retrieves a teacher by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The TeacherDTO of the requested teacher.
     * @throws TeacherServiceException if the teacher is not found or there is an error.
     */
    TeacherDTO getTeacherById(int id) throws TeacherServiceException;

    /**
     * Retrieves all teachers.
     *
     * @return A list of all TeacherDTOs.
     * @throws TeacherServiceException if there is an error retrieving the teachers.
     */
    List<TeacherDTO> getAllTeachers() throws TeacherServiceException;

    /**
     * Updates the details of an existing teacher.
     *
     * @param id The ID of the teacher to update.
     * @param teacherDTO The updated teacher details.
     * @return The updated TeacherDTO.
     * @throws TeacherServiceException if there is an error during the update.
     */
    TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO) throws TeacherServiceException;

    /**
     * Deletes a teacher by their ID.
     *
     * @param id The ID of the teacher to delete.
     * @throws TeacherServiceException if there is an error during the deletion.
     */
    void deleteTeacher(int id) throws TeacherServiceException;
}