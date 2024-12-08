package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.ITeacherDepartmentDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.TeacherDepartmentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.TeacherDepartmentDTO;
import gr.perisnik.cj.schoolapprest.model.TeacherDepartment;
import gr.perisnik.cj.schoolapprest.service.exceptions.TeacherDepartmentServiceException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Teacher-Department relationship operations.
 * Implements the methods defined in ITeacherDepartmentService.
 */
public class TeacherDepartmentServiceImpl implements ITeacherDepartmentService {

    private final ITeacherDepartmentDAO teacherDepartmentDAO;

    // Constructor for dependency injection
    public TeacherDepartmentServiceImpl(ITeacherDepartmentDAO teacherDepartmentDAO) {
        this.teacherDepartmentDAO = teacherDepartmentDAO;
    }

    @Override
    public List<TeacherDepartmentDTO> getAllTeacherDepartments() throws TeacherDepartmentServiceException {
        try {
            List<TeacherDepartment> teacherDepartments = teacherDepartmentDAO.getAllTeacherDepartments();
            return teacherDepartments.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new TeacherDepartmentServiceException("Error retrieving all teacher-department associations.", e);
        }
    }

    @Override
    public List<TeacherDepartmentDTO> getDepartmentsByTeacherId(int teacherId) throws TeacherDepartmentServiceException {
        try {
            List<TeacherDepartment> teacherDepartments = teacherDepartmentDAO.getDepartmentsByTeacherId(teacherId);
            return teacherDepartments.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new TeacherDepartmentServiceException("Error retrieving departments by teacher ID.", e);
        }
    }

    @Override
    public List<TeacherDepartmentDTO> getTeachersByDepartmentId(int departmentId) throws TeacherDepartmentServiceException {
        try {
            List<TeacherDepartment> teacherDepartments = teacherDepartmentDAO.getTeachersByDepartmentId(departmentId);
            return teacherDepartments.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new TeacherDepartmentServiceException("Error retrieving teachers by department ID.", e);
        }
    }

    @Override
    public TeacherDepartmentDTO addAssociation(TeacherDepartmentDTO teacherDepartmentDTO) throws TeacherDepartmentServiceException {
        try {
            // Convert DTO to model
            TeacherDepartment teacherDepartment = convertToModel(teacherDepartmentDTO);
            // Add the association using the DAO
            teacherDepartment = teacherDepartmentDAO.addAssociation(teacherDepartment);
            // Return the DTO after successful association
            return convertToDTO(teacherDepartment);
        } catch (DataAccessException e) {
            throw new TeacherDepartmentServiceException("Error adding teacher-department association.", e);
        }
    }

    @Override
    public void removeAssociation(int teacherId, int departmentId) throws TeacherDepartmentServiceException {
        try {
            teacherDepartmentDAO.removeAssociation(teacherId, departmentId);
        } catch (TeacherDepartmentNotFoundException e) {
            throw new TeacherDepartmentServiceException("Teacher-Department association not found.", e);
        } catch (DataAccessException e) {
            throw new TeacherDepartmentServiceException("Error removing teacher-department association.", e);
        }
    }

    // Helper method to convert from model to DTO
    private TeacherDepartmentDTO convertToDTO(TeacherDepartment teacherDepartment) {
        TeacherDepartmentDTO dto = new TeacherDepartmentDTO();
        dto.setTeacherId(teacherDepartment.getTeacherId());
        dto.setDepartmentId(teacherDepartment.getDepartmentId());
        return dto;
    }

    // Helper method to convert from DTO to model
    private TeacherDepartment convertToModel(TeacherDepartmentDTO teacherDepartmentDTO) {
        TeacherDepartment teacherDepartment = new TeacherDepartment();
        teacherDepartment.setTeacherId(teacherDepartmentDTO.getTeacherId());
        teacherDepartment.setDepartmentId(teacherDepartmentDTO.getDepartmentId());
        return teacherDepartment;
    }
}