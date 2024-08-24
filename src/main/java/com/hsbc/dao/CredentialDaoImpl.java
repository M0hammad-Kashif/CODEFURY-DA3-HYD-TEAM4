package com.hsbc.dao;

import com.hsbc.utilities.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialDaoImpl implements CredentialDao {

    @Override
    public void add(String username, String password) {
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
    public boolean verify(String username, String password) {
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
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }
}
