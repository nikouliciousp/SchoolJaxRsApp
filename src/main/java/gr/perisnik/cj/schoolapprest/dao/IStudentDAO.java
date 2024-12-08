package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Student;
import gr.perisnik.cj.schoolapprest.dao.exceptions.StudentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Student Data Access Object.
 * Defines CRUD operations for Student entities.
 */
public interface IStudentDAO {

    /**
     * Retrieves all students from the database.
     *
     * @return a list of all students.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    List<Student> getAllStudents() throws DataAccessException;

    /**
     * Retrieves a student by their ID.
     *
     * @param id the ID of the student.
     * @return the student with the specified ID.
     * @throws StudentNotFoundException if the student is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    Student getStudentById(int id) throws StudentNotFoundException, DataAccessException;

    /**
     * Adds a new student to the database.
     *
     * @param student the student to add.
     * @return the added student with its generated ID.
     * @throws DataAccessException if there is an issue accessing the data.
     */
    Student addStudent(Student student) throws DataAccessException;

    /**
     * Updates an existing student's details.
     *
     * @param student the student to update.
     * @throws StudentNotFoundException if the student to update is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    void updateStudent(Student student) throws StudentNotFoundException, DataAccessException;

    /**
     * Deletes a student from the database by their ID.
     *
     * @param id the ID of the student to delete.
     * @throws StudentNotFoundException if the student to delete is not found.
     * @throws DataAccessException      if there is an issue accessing the data.
     */
    void deleteStudent(int id) throws StudentNotFoundException, DataAccessException;
}