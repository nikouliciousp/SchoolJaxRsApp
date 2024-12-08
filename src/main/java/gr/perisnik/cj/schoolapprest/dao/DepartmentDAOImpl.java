package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.Department;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DepartmentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of IDepartmentDAO interface for Department entities.
 */
public class DepartmentDAOImpl implements IDepartmentDAO {

    @Override
    public List<Department> getAllDepartments() throws DataAccessException {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";  // Assuming the table name is 'departments'

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                department.setHeadTeacherId(rs.getInt("head_teacher_id"));
                departments.add(department);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving departments.", e);
        }
        return departments;
    }

    @Override
    public Department getDepartmentById(int id) throws DepartmentNotFoundException, DataAccessException {
        Department department = null;
        String sql = "SELECT * FROM departments WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    department = new Department();
                    department.setId(rs.getInt("id"));
                    department.setName(rs.getString("name"));
                    department.setHeadTeacherId(rs.getInt("head_teacher_id"));
                }
            }

            if (department == null) {
                throw new DepartmentNotFoundException("Department with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving department by ID.", e);
        }
        return department;
    }

    @Override
    public Department addDepartment(Department department) throws DataAccessException {
        String sql = "INSERT INTO departments (name, head_teacher_id) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, department.getName());
            ps.setInt(2, department.getHeadTeacherId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Error inserting department.", null);
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    department.setId(rs.getInt(1));  // Setting the generated ID
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error adding new department.", e);
        }
        return department;
    }

    @Override
    public void updateDepartment(Department department) throws DepartmentNotFoundException, DataAccessException {
        String sql = "UPDATE departments SET name = ?, head_teacher_id = ? WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, department.getName());
            ps.setInt(2, department.getHeadTeacherId());
            ps.setInt(3, department.getId());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DepartmentNotFoundException("Department with ID " + department.getId() + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error updating department.", e);
        }
    }

    @Override
    public void deleteDepartment(int id) throws DepartmentNotFoundException, DataAccessException {
        String sql = "DELETE FROM departments WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DepartmentNotFoundException("Department with ID " + id + " not found.");
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error deleting department.", e);
        }
    }
}