package gr.perisnik.cj.schoolapprest.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Course.
 */
public class CourseDTO {
    private int id; // Unique identifier for the course
    private String name; // Name of the course
    private String description; // Description of the course
    private int credits; // Credits assigned to the course
    private int teacherId; // Id of the teacher responsible for the course

    // Default constructor
    public CourseDTO() {
    }

    // Parameterized constructor
    public CourseDTO(int id, String name, String description, int credits, int teacherId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.teacherId = teacherId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    // toString method
    @Override
    public String toString() {
        return "CourseDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return id == courseDTO.id && credits == courseDTO.credits
                && Objects.equals(name, courseDTO.name)
                && Objects.equals(description, courseDTO.description)
                && Objects.equals(teacherId, courseDTO.teacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, credits, teacherId);
    }
}