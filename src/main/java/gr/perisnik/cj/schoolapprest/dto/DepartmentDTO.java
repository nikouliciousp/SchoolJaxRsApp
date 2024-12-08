package gr.perisnik.cj.schoolapprest.dto;

/**
 * Data Transfer Object for Department.
 */
public class DepartmentDTO {

    private int id;           // The ID of the department
    private String name;      // The name of the department
    private int headTeacherId; // The ID of the department head (teacher)

    // Getters and setters

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

    // Constructor for convenience
    public DepartmentDTO(int id, String name, int headTeacherId) {
        this.id = id;
        this.name = name;
        this.headTeacherId = headTeacherId;
    }

    // Default constructor
    public DepartmentDTO() {
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", headTeacherId=" + headTeacherId +
                '}';
    }
}