package gr.perisnik.cj.schoolapprest.model;

import java.util.Objects;

/**
 * Represents a department in the SchoolApp system.
 *
 * @version 1.0
 */
public class Department {
    private int id; // Unique identifier for the department
    private String name; // Name of the department
    private int headTeacherId; // ID of the head teacher of the department

    // Default constructor
    public Department() {
    }

    // Parameterized constructor
    public Department(int id, String name, int headTeacherId) {
        this.id = id;
        this.name = name;
        this.headTeacherId = headTeacherId;
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

    public int getHeadTeacherId() {
        return headTeacherId;
    }

    public void setHeadTeacherId(int headTeacherId) {
        this.headTeacherId = headTeacherId;
    }

    // toString method
    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headTeacherId=" + headTeacherId +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return id == that.id && headTeacherId == that.headTeacherId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, headTeacherId);
    }
}