package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.ITeacherDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.TeacherDTO;
import gr.perisnik.cj.schoolapprest.model.Teacher;
import gr.perisnik.cj.schoolapprest.service.exceptions.TeacherServiceException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class TeacherServiceImpl implements ITeacherService {

    @Inject
    private ITeacherDAO teacherDAO; // Injecting the TeacherDAO for database operations

    /**
     * Adds a new teacher.
     *
     * @param teacherDTO The teacher data transfer object containing the teacher details.
     * @return The created TeacherDTO.
     * @throws TeacherServiceException if there is an error during the teacher creation.
     */
    @Override
    public TeacherDTO addTeacher(TeacherDTO teacherDTO) throws TeacherServiceException {
        try {
            // Create a new Teacher model object and set its properties
            Teacher teacher = new Teacher();
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setEmail(teacherDTO.getEmail());
            teacher.setDayOfBirth(teacherDTO.getDayOfBirth());
            teacher.setPhone(teacherDTO.getPhone());
            teacher.setDepartment(teacherDTO.getDepartment());

            // Add the teacher to the database
            teacher = teacherDAO.addTeacher(teacher);

            // Return the created teacher as a DTO by calling the private method
            return convertToDTO(teacher);
        } catch (DataAccessException e) {
            throw new TeacherServiceException("Error adding teacher.", e);
        }
    }

    /**
     * Retrieves a teacher by their ID.
     *
     * @param id The ID of the teacher to retrieve.
     * @return The TeacherDTO of the requested teacher.
     * @throws TeacherServiceException if the teacher is not found or there is an error.
     */
    @Override
    public TeacherDTO getTeacherById(int id) throws TeacherServiceException {
        try {
            Teacher teacher = teacherDAO.getTeacherById(id);
            if (teacher == null) {
                throw new TeacherServiceException("Teacher with ID " + id + " not found.");
            }
            return convertToDTO(teacher);
        } catch (DataAccessException e) {
            throw new TeacherServiceException("Error retrieving teacher with ID " + id, e);
        }
    }

    /**
     * Retrieves all teachers.
     *
     * @return A list of all TeacherDTOs.
     * @throws TeacherServiceException if there is an error retrieving the teachers.
     */
    @Override
    public List<TeacherDTO> getAllTeachers() throws TeacherServiceException {
        try {
            List<Teacher> teachers = teacherDAO.getAllTeachers();
            List<TeacherDTO> teacherDTOs = new ArrayList<>();
            for (Teacher teacher : teachers) {
                teacherDTOs.add(convertToDTO(teacher));
            }
            return teacherDTOs;
        } catch (DataAccessException e) {
            throw new TeacherServiceException("Error retrieving all teachers.", e);
        }
    }

    /**
     * Updates the details of an existing teacher.
     *
     * @param id The ID of the teacher to update.
     * @param teacherDTO The updated teacher details.
     * @return The updated TeacherDTO.
     * @throws TeacherServiceException if there is an error during the update.
     */
    @Override
    public TeacherDTO updateTeacher(int id, TeacherDTO teacherDTO) throws TeacherServiceException {
        try {
            // Create a new Teacher model object with updated data
            Teacher teacher = new Teacher();
            teacher.setId(id);
            teacher.setFirstName(teacherDTO.getFirstName());
            teacher.setLastName(teacherDTO.getLastName());
            teacher.setEmail(teacherDTO.getEmail());
            teacher.setDayOfBirth(teacherDTO.getDayOfBirth());
            teacher.setPhone(teacherDTO.getPhone());
            teacher.setDepartment(teacherDTO.getDepartment());

            // Update the teacher in the database
            teacherDAO.updateTeacher(teacher);

            // Return the updated teacher as a DTO by calling the private method
            return convertToDTO(teacher);
        } catch (DataAccessException e) {
            throw new TeacherServiceException("Error updating teacher with ID " + id, e);
        }
    }

    /**
     * Deletes a teacher by their ID.
     *
     * @param id The ID of the teacher to delete.
     * @throws TeacherServiceException if there is an error during the deletion.
     */
    @Override
    public void deleteTeacher(int id) throws TeacherServiceException {
        try {
            teacherDAO.deleteTeacher(id);
        } catch (DataAccessException e) {
            throw new TeacherServiceException("Error deleting teacher with ID " + id, e);
        }
    }

    /**
     * Private method to convert a Teacher model to a TeacherDTO.
     *
     * @param teacher The teacher model object to convert.
     * @return The corresponding TeacherDTO.
     */
    private TeacherDTO convertToDTO(Teacher teacher) {
        if (teacher == null) {
            return null;
        }
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setFirstName(teacher.getFirstName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setEmail(teacher.getEmail());
        teacherDTO.setDayOfBirth(teacher.getDayOfBirth());
        teacherDTO.setPhone(teacher.getPhone());
        teacherDTO.setDepartment(teacher.getDepartment());
        return teacherDTO;
    }
}
