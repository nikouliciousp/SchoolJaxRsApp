package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Department;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DepartmentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;

import java.util.List;

/**
 * Interface for Department Data Access Object.
 * Defines CRUD operations for Department entities.
 */
public interface IDepartmentDAO {

    /**
     * Retrieves all departments from the database.
     *
     * @return List of departments
     * @throws DataAccessException If an error occurs while accessing the data
     */
    List<Department> getAllDepartments() throws DataAccessException;

    /**
     * Retrieves a department by its ID.
     *
     * @param id The ID of the department
     * @return The department object
     * @throws DepartmentNotFoundException If the department with the given ID is not found
     * @throws DataAccessException If an error occurs while accessing the data
     */
    Department getDepartmentById(int id) throws DepartmentNotFoundException, DataAccessException;

    /**
     * Adds a new department to the database.
     *
     * @param department The department to be added
     * @return The added department with generated ID
     * @throws DataAccessException If an error occurs while inserting the department
     */
    Department addDepartment(Department department) throws DataAccessException;

    /**
     * Updates the details of an existing department.
     *
     * @param department The department with updated details
     * @throws DepartmentNotFoundException If the department with the given ID does not exist
     * @throws DataAccessException If an error occurs while updating the department
     */
    void updateDepartment(Department department) throws DepartmentNotFoundException, DataAccessException;

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to be deleted
     * @throws DepartmentNotFoundException If the department with the given ID is not found
     * @throws DataAccessException If an error occurs while deleting the department
     */
    void deleteDepartment(int id) throws DepartmentNotFoundException, DataAccessException;
}