package gr.perisnik.cj.schoolapprest.dto;

/**
 * Data Transfer Object for User.
 */
public class UserDTO {
    private int id; // Unique identifier for the user
    private String username; // Username of the user
    private String password; // Password of the user

    // Default constructor
    public UserDTO() {
    }

    // Constructor with parameters
    public UserDTO(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}