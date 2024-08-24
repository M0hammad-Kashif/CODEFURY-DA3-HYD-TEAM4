package com.hsbc.dao;

import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.IncorrectUsernameOrPasswordException;
import com.hsbc.exceptions.WeakPasswordException;
import com.hsbc.utilities.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialDaoImpl implements CredentialDao {

    @Override
    public void add(String username, String password) throws EmployeeDoesNotExistException, WeakPasswordException {
        // check if employee id does not exist
        if(!employeeIdExists(Integer.parseInt(username))){
            throw new EmployeeDoesNotExistException("Employee with this employee id doesn't exist!");
        }

        // check strength of password
        if(!isStrongPassword(password)){
            throw new WeakPasswordException("Password must contain at least 1 lowercase, 1 uppercase, 1 special, 1 numeric character and must be of 8 characters or longer!");
        }

        // create SQL command
        String sql = "INSERT INTO Credentials (username, password) VALUES (?, ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verify(String username, String password) throws IncorrectUsernameOrPasswordException {
        // check if username does not exist
        if(!usernameExists(username)){
            throw new IncorrectUsernameOrPasswordException("Please enter the correct username and password!");
        }

        // create SQL command
        String sql = "SELECT password FROM Credentials WHERE username = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, username);

            // execution returns the password, compare it with input password and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");

                    // check if password is correct else throw exception
                    if(storedPassword.equals(password)){
                        return true;
                    } else {
                        throw new IncorrectUsernameOrPasswordException("Please enter the correct username and password!");
                    }
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    // method to validate if user entered a strong password for the account
    private boolean isStrongPassword(String password){
        // (?=.*[a-z]) The string must contain at least 1 lowercase alphabetical character
        // (?=.*[A-Z]) The string must contain at least 1 uppercase alphabetical character
        // (?=.*[0-9]) The string must contain at least 1 numeric character
        // (?=.[!@#$%^&]) The string must contain at least one special character
        // (?=.{8,}) The string must be eight characters or longer
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})");
    }

    // method to check if employee id exists
    private boolean employeeIdExists(int employeeId) {
        // create SQL command
        String sql = "SELECT * FROM Employee WHERE employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, employeeId);

            // if execution returns 1 or more rows, return true, else false
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    // method to check if username exists in credentials db
    private boolean usernameExists(String username) {
        // create SQL command
        String sql = "SELECT * FROM Credentials WHERE username = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, username);

            // if execution returns 1 row, return true, else false
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
