package gr.perisnik.cj.schoolapprest.model;

import java.util.Objects;

/**
 * Represents a student in the SchoolApp system.
 *
 * @version 1.0
 */
public class Student {
    private int id; // Unique identifier for the student
    private String firstName; // First name of the student
    private String lastName; // Last name of the student
    private String email; // Email of the student
    private String dayOfBirth; // Birthdate of the student
    private String phone; // Phone number of the student

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int id, String firstName, String lastName, String email, String dayOfBirth, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dayOfBirth = dayOfBirth;
        this.phone = phone;
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

    // toString method
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dayOfBirth='" + dayOfBirth + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    // Equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(email, student.email) && Objects.equals(dayOfBirth, student.dayOfBirth) && Objects.equals(phone, student.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, dayOfBirth, phone);
    }
}