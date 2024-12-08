package gr.perisnik.cj.schoolapprest.dao;

import gr.perisnik.cj.schoolapprest.model.TeacherDepartment;
import gr.perisnik.cj.schoolapprest.dao.exceptions.TeacherDepartmentNotFoundException;
import gr.perisnik.cj.schoolapprest.dao.exceptions.DataAccessException;
import gr.perisnik.cj.schoolapprest.dao.dbutil.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ITeacherDepartmentDAO interface for managing Teacher-Department associations.
 */
public class TeacherDepartmentDAOImpl implements ITeacherDepartmentDAO {

    @Override
    public List<TeacherDepartment> getAllTeacherDepartments() throws DataAccessException {
        List<TeacherDepartment> teacherDepartmentList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_department";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TeacherDepartment teacherDepartment = new TeacherDepartment();
                teacherDepartment.setTeacherId(rs.getInt("teacher_id"));
                teacherDepartment.setDepartmentId(rs.getInt("department_id"));
                teacherDepartmentList.add(teacherDepartment);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving teacher-department associations.", e);
        }
        return teacherDepartmentList;
    }

    @Override
    public List<TeacherDepartment> getDepartmentsByTeacherId(int teacherId) throws DataAccessException {
        List<TeacherDepartment> teacherDepartmentList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_department WHERE teacher_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TeacherDepartment teacherDepartment = new TeacherDepartment();
                    teacherDepartment.setTeacherId(rs.getInt("teacher_id"));
                    teacherDepartment.setDepartmentId(rs.getInt("department_id"));
                    teacherDepartmentList.add(teacherDepartment);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving departments by teacher ID.", e);
        }
        return teacherDepartmentList;
    }

    @Override
    public List<TeacherDepartment> getTeachersByDepartmentId(int departmentId) throws DataAccessException {
        List<TeacherDepartment> teacherDepartmentList = new ArrayList<>();
        String sql = "SELECT * FROM teacher_department WHERE department_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, departmentId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    TeacherDepartment teacherDepartment = new TeacherDepartment();
                    teacherDepartment.setTeacherId(rs.getInt("teacher_id"));
                    teacherDepartment.setDepartmentId(rs.getInt("department_id"));
                    teacherDepartmentList.add(teacherDepartment);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error retrieving teachers by department ID.", e);
        }
        return teacherDepartmentList;
    }

    @Override
    public TeacherDepartment addAssociation(TeacherDepartment teacherDepartment) throws DataAccessException {
        String sql = "INSERT INTO teacher_department (teacher_id, department_id) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, teacherDepartment.getTeacherId());
            ps.setInt(2, teacherDepartment.getDepartmentId());
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new DataAccessException("Error inserting teacher-department association.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error adding teacher-department association.", e);
        }
        return teacherDepartment;
    }

    @Override
    public void removeAssociation(int teacherId, int departmentId) throws TeacherDepartmentNotFoundException, DataAccessException {
        String sql = "DELETE FROM teacher_department WHERE teacher_id = ? AND department_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, teacherId);
            ps.setInt(2, departmentId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 0) {
                throw new TeacherDepartmentNotFoundException("Association between teacher ID " + teacherId + " and department ID " + departmentId + " not found.", null);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new DataAccessException("Error removing teacher-department association.", e);
        }
    }
}