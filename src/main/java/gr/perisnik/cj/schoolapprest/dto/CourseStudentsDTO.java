package gr.perisnik.cj.schoolapprest.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Course-Student relationship.
 */
public class CourseStudentsDTO {
    private int courseId; // ID of the course
    private int studentId; // ID of the student
    private String enrollmentDate; // Date of enrollment

    // Default constructor
    public CourseStudentsDTO() {
    }

    // Parameterized constructor
    public CourseStudentsDTO(int courseId, int studentId, String enrollmentDate) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    // toString method
    @Override
    public String toString() {
        return "CourseStudentsDTO{" +
                "courseId=" + courseId +
                ", studentId=" + studentId +
                ", enrollmentDate='" + enrollmentDate + '\'' +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentsDTO that = (CourseStudentsDTO) o;
        return courseId == that.courseId && studentId == that.studentId && Objects.equals(enrollmentDate, that.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId, enrollmentDate);
    }
}