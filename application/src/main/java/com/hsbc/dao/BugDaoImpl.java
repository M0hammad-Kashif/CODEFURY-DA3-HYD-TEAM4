package com.hsbc.dao;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Employee;
import com.hsbc.exceptions.BugAlreadyClosedException;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.utilities.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BugDaoImpl implements BugDao {

    @Override
    public int create(int projectId, String title, String description, String severityLevel, int reportedBy) {
        // create sql command
        String sql = "INSERT INTO Bug (projectId, title, description, severityLevel, createdOn, status, reportedBy) VALUES (?, ?, ?, ?, ?, 'created', ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, projectId);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, severityLevel);
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(6, reportedBy);
            pstmt.executeUpdate();

            // traverse the resultSet and return the unique bug id
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
    public void assignToDev(int bugId, int employeeId) throws BugDoesNotExistException, BugAlreadyClosedException, EmployeeDoesNotExistException {
        // check if bug exists
        findById(bugId);
        // check if employee exists
        EmployeeDaoImpl emp = new EmployeeDaoImpl();
        emp.findById(employeeId);
        // check if bug is closed
        if(isBugClosed(bugId)){
            throw new BugAlreadyClosedException("This bug is already closed!");
        }

        // create sql command
        String sql = "UPDATE Bug SET assignedTo = ? WHERE bugId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, employeeId);
            pstmt.setInt(2, bugId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bug findById(int bugId) throws BugDoesNotExistException {
        // create sql command
        String sql = "SELECT * FROM Bug WHERE bugId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setInt(1, bugId);

            // execution returns a bug, extract it and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBugFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new BugDoesNotExistException("No bug exists with this id!");
    }

    @Override
    public Bug findByName(String bugName) throws BugDoesNotExistException {
        // create sql command
        String sql = "SELECT * FROM Bug WHERE title = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, bugName);

            // execution returns a bug, extract it and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBugFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new BugDoesNotExistException("No bug exists with this name!");
    }

    @Override
    public void changeAssignedEmployee(int bugId, int employeeId) throws BugDoesNotExistException, BugAlreadyClosedException, EmployeeDoesNotExistException {
        // check if bug exists
        findById(bugId);

        // use assignToDev method to update assigned developer
        assignToDev(bugId, employeeId);
    }

    @Override
    public void resolve(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException {
        // check if bug exists
        findById(bugId);
        // check if bug is closed
        if(isBugClosed(bugId)){
            throw new BugAlreadyClosedException("Can't resolve, bug already closed!");
        }

        updateBugStatus(bugId, "resolved");
    }

    @Override
    public void close(int bugId) throws BugDoesNotExistException {
        // check if bug exists
        findById(bugId);

        updateBugStatus(bugId, "closed");
    }

    // generalised method to update bug status
    private void updateBugStatus(int bugId, String status) {


        // create sql command
        String sql = "UPDATE Bug SET status = ? WHERE bugId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values into the statement and execute
            pstmt.setString(1, status);
            pstmt.setInt(2, bugId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // method to create bug object, set values and return
    private Bug extractBugFromResultSet(ResultSet rs) throws SQLException {
        Bug bug = new Bug();

        bug.setBugId(rs.getInt("bugId"));
        bug.setProjectId(rs.getInt("projectId"));
        bug.setTitle(rs.getString("title"));
        bug.setDescription(rs.getString("description"));
        bug.setSeverityLevel(Bug.Level.valueOf(rs.getString("severityLevel")));
        bug.setReportedOn(LocalDate.from(rs.getTimestamp("createdOn").toLocalDateTime()));
        bug.setStatus(Bug.BugStatus.valueOf(rs.getString("status")));
        bug.setReportedBy(rs.getInt("reportedBy"));
        bug.setAssignedTo(rs.getInt("assignedTo"));

        return bug;
    }

    // method to check if bug status is closed
    private boolean isBugClosed(int bugId){
        try {
            Bug bug = findById(bugId);
            return bug.getStatus().equals(Bug.BugStatus.valueOf("closed"));
        } catch (BugDoesNotExistException e) {
            e.printStackTrace();
        }
        return false;
    }
}
