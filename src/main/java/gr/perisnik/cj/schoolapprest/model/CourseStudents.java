package gr.perisnik.cj.schoolapprest.model;

import java.util.Objects;

/**
 * Represents the association between a course and its enrolled students in the SchoolApp system.
 *
 * @version 1.0
 */
public class CourseStudents {
    private int courseId; // ID of the course
    private int studentId; // ID of the student
    private String enrollmentDate; // Date of enrollment in the course

    // Default constructor
    public CourseStudents() {
    }

    // Parameterized constructor
    public CourseStudents(int courseId, int studentId, String enrollmentDate) {
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
        return "CourseStudents{" +
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
        CourseStudents that = (CourseStudents) o;
        return courseId == that.courseId && studentId == that.studentId && Objects.equals(enrollmentDate, that.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId, enrollmentDate);
    }
}