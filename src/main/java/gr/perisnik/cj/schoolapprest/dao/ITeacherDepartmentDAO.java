package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.TeacherDepartment;
import gr.perisnik.cj.schoolapprest.dao.exceptions.TeacherDepartmentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Teacher-Department Data Access Object.
 * Defines CRUD operations for the relationship between Teacher and Department entities.
 */
public interface ITeacherDepartmentDAO {

    /**
     * Retrieves all associations (Teacher-Department relationships) from the database.
     *
     * @return List of TeacherDepartment objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<TeacherDepartment> getAllTeacherDepartments() throws DataAccessException;

    /**
     * Retrieves all departments associated with a specific teacher.
     *
     * @param teacherId The ID of the teacher
     * @return List of TeacherDepartment objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<TeacherDepartment> getDepartmentsByTeacherId(int teacherId) throws DataAccessException;

    /**
     * Retrieves all teachers associated with a specific department.
     *
     * @param departmentId The ID of the department
     * @return List of TeacherDepartment objects
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<TeacherDepartment> getTeachersByDepartmentId(int departmentId) throws DataAccessException;

    /**
     * Associates a teacher with a department.
     *
     * @param teacherDepartment The TeacherDepartment object containing teacher and department information
     * @return The created TeacherDepartment object
     * @throws DataAccessException If an error occurs while inserting the association data
     */
    TeacherDepartment addAssociation(TeacherDepartment teacherDepartment) throws DataAccessException;

    /**
     * Removes the association between a teacher and a department.
     *
     * @param teacherId The ID of the teacher
     * @param departmentId The ID of the department
     * @throws TeacherDepartmentNotFoundException If the association between the teacher and department does not exist
     * @throws DataAccessException If an error occurs while deleting the association
     */
    void removeAssociation(int teacherId, int departmentId) throws TeacherDepartmentNotFoundException, DataAccessException;
}