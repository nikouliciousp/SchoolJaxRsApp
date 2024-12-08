package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Teacher;
import gr.perisnik.cj.schoolapprest.dao.exceptions.TeacherNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Teacher Data Access Object.
 * Defines CRUD operations for Teacher entities.
 */
public interface ITeacherDAO {

    /**
     * Retrieves all teachers.
     *
     * @return a list of all teachers.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    List<Teacher> getAllTeachers() throws DataAccessException;

    /**
     * Retrieves a teacher by their ID.
     *
     * @param id the ID of the teacher.
     * @return the teacher with the specified ID.
     * @throws TeacherNotFoundException if the teacher is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    Teacher getTeacherById(int id) throws TeacherNotFoundException, DataAccessException;

    /**
     * Adds a new teacher.
     *
     * @param teacher the teacher to add.
     * @return the added teacher with its generated ID.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    Teacher addTeacher(Teacher teacher) throws DataAccessException;

    /**
     * Updates an existing teacher.
     *
     * @param teacher the teacher to update.
     * @throws TeacherNotFoundException if the teacher to update is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    void updateTeacher(Teacher teacher) throws TeacherNotFoundException, DataAccessException;

    /**
     * Deletes a teacher by their ID.
     *
     * @param id the ID of the teacher to delete.
     * @throws TeacherNotFoundException if the teacher to delete is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    void deleteTeacher(int id) throws TeacherNotFoundException, DataAccessException;
}