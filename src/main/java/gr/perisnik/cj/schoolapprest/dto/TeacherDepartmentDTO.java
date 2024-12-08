package gr.perisnik.cj.schoolapprest.dto;

import java.util.Objects;

/**
 * Data Transfer Object for the association between a teacher and a department in the SchoolApp system.
 *
 * @version 1.0
 */
public class TeacherDepartmentDTO {
    private int teacherId; // ID of the teacher
    private int departmentId; // ID of the department

    // Default constructor
    public TeacherDepartmentDTO() {
    }

    // Parameterized constructor
    public TeacherDepartmentDTO(int teacherId, int departmentId) {
        this.teacherId = teacherId;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    // toString method
    @Override
    public String toString() {
        return "TeacherDepartmentDTO{" +
                "teacherId=" + teacherId +
                ", departmentId=" + departmentId +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDepartmentDTO that = (TeacherDepartmentDTO) o;
        return teacherId == that.teacherId && departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherId, departmentId);
    }
}