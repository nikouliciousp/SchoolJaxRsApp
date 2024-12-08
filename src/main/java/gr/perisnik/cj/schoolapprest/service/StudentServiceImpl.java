package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.IStudentDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.StudentDTO;
import gr.perisnik.cj.schoolapprest.model.Student;
import gr.perisnik.cj.schoolapprest.service.exceptions.StudentServiceException;

import javax.inject.Inject;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class StudentServiceImpl implements IStudentService {

    @Inject
    private IStudentDAO studentDAO; // Injecting the StudentDAO for database operations

    /**
     * Adds a new student.
     *
     * @param studentDTO The student data transfer object containing the student details.
     * @return The created StudentDTO.
     * @throws StudentServiceException if there is an error during the student creation.
     */
    @Override
    public StudentDTO addStudent(StudentDTO studentDTO) throws StudentServiceException {
        try {
            // Create a new Student model object and set its properties
            Student student = new Student();
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setEmail(studentDTO.getEmail());
            student.setDayOfBirth(studentDTO.getDayOfBirth());
            student.setPhone(studentDTO.getPhone());

            // Add the student to the database
            student = studentDAO.addStudent(student);

            // Return the created student as a DTO by calling the private method
            return convertToDTO(student);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error adding student.", e);
        }
    }

    /**
     * Retrieves a student by their ID.
     *
     * @param id The ID of the student to retrieve.
     * @return The StudentDTO of the requested student.
     * @throws StudentServiceException if the student is not found or there is an error.
     */
    @Override
    public StudentDTO getStudentById(int id) throws StudentServiceException {
        try {
            Student student = studentDAO.getStudentById(id);
            if (student == null) {
                throw new StudentServiceException("Student with ID " + id + " not found.");
            }
            return convertToDTO(student);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error retrieving student with ID " + id, e);
        }
    }

    /**
     * Retrieves all students.
     *
     * @return A list of all StudentDTOs.
     * @throws StudentServiceException if there is an error retrieving the students.
     */
    @Override
    public List<StudentDTO> getAllStudents() throws StudentServiceException {
        try {
            List<Student> students = studentDAO.getAllStudents();
            List<StudentDTO> studentDTOs = new ArrayList<>();
            for (Student student : students) {
                studentDTOs.add(convertToDTO(student));
            }
            return studentDTOs;
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error retrieving all students.", e);
        }
    }

    /**
     * Updates the details of an existing student.
     *
     * @param id The ID of the student to update.
     * @param studentDTO The updated student details.
     * @return The updated StudentDTO.
     * @throws StudentServiceException if there is an error during the update.
     */
    @Override
    public StudentDTO updateStudent(int id, StudentDTO studentDTO) throws StudentServiceException {
        try {
            // Create a new Student model object with updated data
            Student student = new Student();
            student.setId(id);
            student.setFirstName(studentDTO.getFirstName());
            student.setLastName(studentDTO.getLastName());
            student.setEmail(studentDTO.getEmail());
            student.setDayOfBirth(studentDTO.getDayOfBirth());
            student.setPhone(studentDTO.getPhone());

            // Update the student in the database
            studentDAO.updateStudent(student);

            // Return the updated student as a DTO by calling the private method
            return convertToDTO(student);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error updating student with ID " + id, e);
        }
    }

    /**
     * Deletes a student by their ID.
     *
     * @param id The ID of the student to delete.
     * @throws StudentServiceException if there is an error during the deletion.
     */
    @Override
    public void deleteStudent(int id) throws StudentServiceException {
        try {
            studentDAO.deleteStudent(id);
        } catch (DataAccessException e) {
            throw new StudentServiceException("Error deleting student with ID " + id, e);
        }
    }

    /**
     * Private method to convert a Student model to a StudentDTO.
     *
     * @param student The student model object to convert.
     * @return The corresponding StudentDTO.
     */
    private StudentDTO convertToDTO(Student student) {
        if (student == null) {
            return null;
        }
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setEmail(student.getEmail());
        studentDTO.setDayOfBirth(student.getDayOfBirth());
        studentDTO.setPhone(student.getPhone());
        return studentDTO;
    }
}