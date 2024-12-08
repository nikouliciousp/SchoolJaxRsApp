package gr.perisnik.cj.schoolapprest.dto;

import java.util.Objects;

/**
 * Data Transfer Object for Teacher.
 */
public class TeacherDTO {
    private int id; // Unique identifier for the teacher
    private String firstName; // First name of the teacher
    private String lastName; // Last name of the teacher
    private String email; // Email of the teacher
    private String dayOfBirth; // Birthdate of the teacher
    private String phone; // Phone number of the teacher
    private int departmentId; // Department name of the teacher

    // Default constructor
    public TeacherDTO() {
    }

    // Parameterized constructor
    public TeacherDTO(int id, String firstName, String lastName, String email, String dayOfBirth, String phone, int departmentId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
        this.departmentId = departmentId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDepartment() {
        return departmentId;
    }

    public void setDepartment(int departmentId) {
        this.departmentId = departmentId;
    }

    // toString method
    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", phone='" + phone + '\'' +
                ", departmentId='" + departmentId + '\'' +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDTO that = (TeacherDTO) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(dayOfBirth, that.dayOfBirth) && Objects.equals(phone, that.phone) && Objects.equals(departmentId, that.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, dayOfBirth, phone, departmentId);
    }
}