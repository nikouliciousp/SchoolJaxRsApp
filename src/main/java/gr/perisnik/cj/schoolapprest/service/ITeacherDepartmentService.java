package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dto.TeacherDepartmentDTO;
import gr.perisnik.cj.schoolapprest.service.exceptions.TeacherDepartmentServiceException;

import java.util.List;

/**
 * Service interface for Teacher-Department relationship operations.
 * Defines methods for adding, removing, and retrieving Teacher-Department associations.
 */
public interface ITeacherDepartmentService {

    /**
     * Retrieves all Teacher-Department associations.
     *
     * @return a list of TeacherDepartmentDTO objects
     * @throws TeacherDepartmentServiceException if an error occurs during retrieval
     */
    List<TeacherDepartmentDTO> getAllTeacherDepartments() throws TeacherDepartmentServiceException;

    /**
     * Retrieves all departments associated with a specific teacher.
     *
     * @param teacherId The ID of the teacher
     * @return a list of TeacherDepartmentDTO objects for the given teacher
     * @throws TeacherDepartmentServiceException if an error occurs during retrieval
     */
    List<TeacherDepartmentDTO> getDepartmentsByTeacherId(int teacherId) throws TeacherDepartmentServiceException;

    /**
     * Retrieves all teachers associated with a specific department.
     *
     * @param departmentId The ID of the department
     * @return a list of TeacherDepartmentDTO objects for the given department
     * @throws TeacherDepartmentServiceException if an error occurs during retrieval
     */
    List<TeacherDepartmentDTO> getTeachersByDepartmentId(int departmentId) throws TeacherDepartmentServiceException;

    /**
     * Adds a new Teacher-Department association.
     *
     * @param teacherDepartmentDTO The DTO containing teacher and department information
     * @return the added TeacherDepartmentDTO object
     * @throws TeacherDepartmentServiceException if an error occurs during the addition
     */
    TeacherDepartmentDTO addAssociation(TeacherDepartmentDTO teacherDepartmentDTO) throws TeacherDepartmentServiceException;

    /**
     * Removes the association between a teacher and a department.
     *
     * @param teacherId The ID of the teacher
     * @param departmentId The ID of the department
     * @throws TeacherDepartmentServiceException if an error occurs during removal
     */
    void removeAssociation(int teacherId, int departmentId) throws TeacherDepartmentServiceException;
}