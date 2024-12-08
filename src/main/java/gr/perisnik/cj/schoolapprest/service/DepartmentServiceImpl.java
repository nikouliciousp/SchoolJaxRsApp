package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.IDepartmentDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.DepartmentDTO;
import gr.perisnik.cj.schoolapprest.model.Department;
import gr.perisnik.cj.schoolapprest.service.exceptions.DepartmentServiceException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class DepartmentServiceImpl implements IDepartmentService {

    @Inject
    private IDepartmentDAO departmentDAO; // Injecting the DepartmentDAO for database operations

    /**
     * Adds a new department.
     *
     * @param departmentDTO The department data transfer object containing the department details.
     * @return The created DepartmentDTO.
     * @throws DepartmentServiceException if there is an error during the department creation.
     */
    @Override
    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO) throws DepartmentServiceException {
        try {
            // Create a new Department model object and set its properties
            Department department = new Department();
            department.setName(departmentDTO.getName());
            department.setHeadTeacherId(departmentDTO.getHeadTeacherId());

            // Add the department to the database
            department = departmentDAO.addDepartment(department);

            // Return the created department as a DTO
            return convertToDTO(department);
        } catch (DataAccessException e) {
            throw new DepartmentServiceException("Error adding department.", e);
        }
    }

    /**
     * Retrieves a department by its ID.
     *
     * @param id The ID of the department to retrieve.
     * @return The DepartmentDTO of the requested department.
     * @throws DepartmentServiceException if the department is not found or there is an error.
     */
    @Override
    public DepartmentDTO getDepartmentById(int id) throws DepartmentServiceException {
        try {
            Department department = departmentDAO.getDepartmentById(id);
            if (department == null) {
                throw new DepartmentServiceException("Department with ID " + id + " not found.");
            }
            return convertToDTO(department);
        } catch (DataAccessException e) {
            throw new DepartmentServiceException("Error retrieving department with ID " + id, e);
        }
    }

    /**
     * Retrieves all departments.
     *
     * @return A list of all DepartmentDTOs.
     * @throws DepartmentServiceException if there is an error retrieving the departments.
     */
    @Override
    public List<DepartmentDTO> getAllDepartments() throws DepartmentServiceException {
        try {
            List<Department> departments = departmentDAO.getAllDepartments();
            List<DepartmentDTO> departmentDTOs = new ArrayList<>();
            for (Department department : departments) {
                departmentDTOs.add(convertToDTO(department));
            }
            return departmentDTOs;
        } catch (DataAccessException e) {
            throw new DepartmentServiceException("Error retrieving all departments.", e);
        }
    }

    /**
     * Updates the details of an existing department.
     *
     * @param id The ID of the department to update.
     * @param departmentDTO The updated department details.
     * @return The updated DepartmentDTO.
     * @throws DepartmentServiceException if there is an error during the update.
     */
    @Override
    public DepartmentDTO updateDepartment(int id, DepartmentDTO departmentDTO) throws DepartmentServiceException {
        try {
            // Create a new Department model object with updated data
            Department department = new Department();
            department.setId(id);
            department.setName(departmentDTO.getName());
            department.setHeadTeacherId(departmentDTO.getHeadTeacherId());

            // Update the department in the database
            departmentDAO.updateDepartment(department);

            // Return the updated department as a DTO
            return convertToDTO(department);
        } catch (DataAccessException e) {
            throw new DepartmentServiceException("Error updating department with ID " + id, e);
        }
    }

    /**
     * Deletes a department by its ID.
     *
     * @param id The ID of the department to delete.
     * @throws DepartmentServiceException if there is an error during the deletion.
     */
    @Override
    public void deleteDepartment(int id) throws DepartmentServiceException {
        try {
            departmentDAO.deleteDepartment(id);
        } catch (DataAccessException e) {
            throw new DepartmentServiceException("Error deleting department with ID " + id, e);
        }
    }

    /**
     * Private method to convert a Department model to a DepartmentDTO.
     *
     * @param department The department model object to convert.
     * @return The corresponding DepartmentDTO.
     */
    private DepartmentDTO convertToDTO(Department department) {
        if (department == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        departmentDTO.setHeadTeacherId(department.getHeadTeacherId());
        return departmentDTO;
    }
}