package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.DepartmentDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.DepartmentServiceException;

import java.util.List;

/**
 * Interface for Department Service.
 * Defines operations for Department entities.
 */
public interface IDepartmentService {

    /**
     * Adds a new department.
     *
     * @param departmentDTO The department data transfer object containing the department details.
     * @return The created DepartmentDTO.
     * @throws DepartmentServiceException if there is an error during department creation.
     */
    DepartmentDTO addDepartment(DepartmentDTO departmentDTO) throws DepartmentServiceException;

    /**
     * Retrieves a department by its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return The DepartmentDTO of the requested department.
     * @throws DepartmentServiceException if the department is not found or there is an error.
     */
    DepartmentDTO getDepartmentById(int id) throws DepartmentServiceException;

    /**
     * Retrieves all departments.
     *
     * @return A list of all DepartmentDTOs.
     * @throws DepartmentServiceException if there is an error retrieving the departments.
     */
    List<DepartmentDTO> getAllDepartments() throws DepartmentServiceException;

    /**
     * Updates the details of an existing department.
     *
     * @param id The ID of the department to update.
     * @param departmentDTO The updated department details.
     * @return The updated DepartmentDTO.
     * @throws DepartmentServiceException if there is an error during the update.
     */
    DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO) throws DepartmentServiceException;

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to delete.
     * @throws DepartmentServiceException if there is an error during the deletion.
     */
    void deleteDepartment(int id) throws DepartmentServiceException;
}