package gr.perisnik.cj.schoolapprest.model;

/**
 * Represents a user in the SchoolApp system.
 *
 * @version 1.0
 * @author Peris Nik
 */
public class User {
    private int id; // Unique identifier for the user
    private String username; // Username of the user
    private String password; // Password of the user

    /**
     * Default constructor
     */
    public User() {
    }

    /**
     * Constructor with parameters
     *
     * @param id       the unique identifier of the user
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the unique identifier of the user
     *
     * @return the id of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the user
     *
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the username of the user
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user
     *
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}