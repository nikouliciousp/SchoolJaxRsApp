package gr.perisnik.cj.schoolapprest.service;

import gr.perisnik.cj.schoolapprest.dao.ICourseStudentsDAO;
import gr.perisnik.cj.schoolapprest.dao.exceptions.CourseStudentsNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dto.CourseStudentsDTO;
import gr.perisnik.cj.schoolapprest.model.CourseStudents;
import gr.perisnik.cj.schoolapprest.service.exceptions.CourseStudentsServiceException;

import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation for Course-Student relationship operations.
 */
@Provider
public class CourseStudentsServiceImpl implements ICourseStudentsService {

    private final ICourseStudentsDAO courseStudentsDAO;

    public CourseStudentsServiceImpl(ICourseStudentsDAO courseStudentsDAO) {
        this.courseStudentsDAO = courseStudentsDAO;
    }

    @Override
    public List<CourseStudentsDTO> getAllCourseStudents() throws CourseStudentsServiceException {
        try {
            List<CourseStudents> courseStudentsList = courseStudentsDAO.getAllCourseStudents();
            return courseStudentsList.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new CourseStudentsServiceException("Error retrieving all course-student relationships.", e);
        }
    }

    @Override
    public CourseStudentsDTO getCourseStudentByIds(int courseId, int studentId) throws CourseStudentsServiceException {
        try {
            CourseStudents courseStudents = courseStudentsDAO.getCoursesByStudentId(studentId)
                    .stream()
                    .filter(cs -> cs.getCourseId() == courseId)
                    .findFirst()
                    .orElseThrow(() -> new CourseStudentsNotFoundException("No enrollment found for this student and course", null));
            return convertToDTO(courseStudents);
        } catch (CourseStudentsNotFoundException e) {
            throw new CourseStudentsServiceException("Course-student relationship not found.", e);
        } catch (DataAccessException e) {
            throw new CourseStudentsServiceException("Error retrieving course-student by course and student IDs.", e);
        }
    }

    @Override
    public CourseStudentsDTO addCourseStudent(CourseStudentsDTO courseStudentsDTO) throws CourseStudentsServiceException {
        try {
            CourseStudents courseStudents = convertToEntity(courseStudentsDTO);
            CourseStudents addedCourseStudent = courseStudentsDAO.addEnrollment(courseStudents);
            return convertToDTO(addedCourseStudent);
        } catch (DataAccessException e) {
            throw new CourseStudentsServiceException("Error adding course-student relationship.", e);
        }
    }

    @Override
    public void deleteCourseStudent(int courseId, int studentId) throws CourseStudentsServiceException {
        try {
            courseStudentsDAO.removeEnrollment(courseId, studentId);
        } catch (CourseStudentsNotFoundException e) {
            throw new CourseStudentsServiceException("Enrollment not found for the given course and student.", e);
        } catch (DataAccessException e) {
            throw new CourseStudentsServiceException("Error removing course-student relationship.", e);
        }
    }

    /**
     * Converts a CourseStudents entity to a CourseStudentsDTO.
     *
     * @param courseStudents the entity to convert.
     * @return the corresponding DTO.
     */
    private CourseStudentsDTO convertToDTO(CourseStudents courseStudents) {
        return new CourseStudentsDTO(
                courseStudents.getCourseId(),
                courseStudents.getStudentId(),
                courseStudents.getEnrollmentDate()
        );
    }

    /**
     * Converts a CourseStudentsDTO to a CourseStudents entity.
     *
     * @param courseStudentsDTO the DTO to convert.
     * @return the corresponding entity.
     */
    private CourseStudents convertToEntity(CourseStudentsDTO courseStudentsDTO) {
        return new CourseStudents(
                courseStudentsDTO.getCourseId(),
                courseStudentsDTO.getStudentId(),
                courseStudentsDTO.getEnrollmentDate()
        );
    }
}