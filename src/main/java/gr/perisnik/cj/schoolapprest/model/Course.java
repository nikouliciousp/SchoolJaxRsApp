package gr.perisnik.cj.schoolapprest.model;

import java.util.Objects;

/**
 * Represents a course in the SchoolApp system.
 *
 * @version 1.0
 */
public class Course {
    private int id; // Unique identifier for the course
    private String name; // Name of the course
    private String description; // Description of the course
    private int credits; // Credits assigned to the course
    private int teacherId; // ID of the teacher responsible for the course

    // Default constructor
    public Course() {
    }

    // Parameterized constructor
    public Course(int id, String name, String description, int credits, int teacherId) {
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
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", credits=" + credits +
                ", teacherId=" + teacherId +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && credits == course.credits && teacherId == course.teacherId && Objects.equals(name, course.name) && Objects.equals(description, course.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, credits, teacherId);
    }
}