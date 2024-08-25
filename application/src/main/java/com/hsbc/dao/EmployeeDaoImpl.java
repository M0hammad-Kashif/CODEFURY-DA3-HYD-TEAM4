package com.hsbc.dao;

import com.hsbc.beans.Employee;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.utilities.DBUtil;

import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public int create(String name, String email, String role) {
        // create sql command
        String sql = "INSERT INTO Employee (name, email, role) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, role);
            pstmt.executeUpdate();

            // traverse the resultSet and return the unique employee id
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Employee findById(int employeeId) throws EmployeeDoesNotExistException {
        // create sql command
        String sql = "SELECT * FROM Employee WHERE employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, employeeId);

            // execution returns a bug, extract it and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractEmployeeFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // if employee not found, throw exception
        throw new EmployeeDoesNotExistException("This employee id does not exist!");
    }

    @Override
    public void updateEmail(int employeeId, String email) throws EmployeeDoesNotExistException {
        // check for valid employeeId - if not exists, then findById throws exception
        findById(employeeId);

        // create sql command
        String sql = "UPDATE Employee SET email = ? WHERE employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, email);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateRole(int employeeId, String role) throws EmployeeDoesNotExistException {
        // check for valid employeeId - if not exists, then findById throws exception
        findById(employeeId);

        // create sql command
        String sql = "UPDATE Employee SET role = ? WHERE employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, role);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int employeeId) throws EmployeeDoesNotExistException {
        // check for valid employeeId - if not exists, then findById throws exception
        findById(employeeId);

        // create sql command
        String sql = "DELETE FROM Employee WHERE employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // method to create employee object, set values and return
    private Employee extractEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        employee.setEmployeeId(rs.getInt("employeeId"));
        employee.setName(rs.getString("name"));
        employee.setEmail(rs.getString("email"));
        employee.setRole(Employee.Role.valueOf(rs.getString("severityLevel")));

        return employee;
    }
}
