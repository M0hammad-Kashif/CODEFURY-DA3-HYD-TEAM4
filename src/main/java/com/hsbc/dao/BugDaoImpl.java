package com.hsbc.dao;

import com.hsbc.beans.Bug;
import com.hsbc.utilities.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BugDaoImpl implements BugDao {

    @Override
    public int create(int projectId, String title, String description, Bug.Level severityLevel, int reportedBy) {
        String sql = "INSERT INTO Bug (projectId, title, description, severityLevel, createdOn, status, reportedBy) VALUES (?, ?, ?, ?, ?, 'created', ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, projectId);
            pstmt.setString(2, title);
            pstmt.setString(3, description);
            pstmt.setString(4, severityLevel.toString().toLowerCase());
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(6, reportedBy);
            pstmt.executeUpdate();
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
    public void assignToDev(int bugId, int employeeId) {
        String sql = "INSERT INTO DeveloperBug (developerId, bugId, assignedDate) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.setInt(2, bugId);
            pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bug findById(int bugId) {
        String sql = "SELECT * FROM Bug WHERE bugId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bugId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBugFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Bug findByName(String bugName) {
        String sql = "SELECT * FROM Bug WHERE title = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bugName);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBugFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeAssignedEmployee(int bugId, int employeeId) {
        String sql = "UPDATE DeveloperBug SET developerId = ?, assignedDate = ? WHERE bugId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, employeeId);
            pstmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(3, bugId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void resolve(int bugId) {
        updateBugStatus(bugId, "resolved");
    }

    @Override
    public void close(int bugId) {
        updateBugStatus(bugId, "closed");
    }

    private void updateBugStatus(int bugId, String status) {
        String sql = "UPDATE Bug SET status = ? WHERE bugId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, bugId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Bug extractBugFromResultSet(ResultSet rs) throws SQLException {
        Bug bug = new Bug();
        bug.setBugId(rs.getInt("bugId"));
        bug.setProjectId(rs.getInt("projectId"));
        bug.setTitle(rs.getString("title"));
        bug.setDescription(rs.getString("description"));
        bug.setSeverityLevel(Bug.Level.valueOf(rs.getString("severityLevel").toUpperCase()));
        bug.setReportedOn(LocalDate.from(rs.getTimestamp("createdOn").toLocalDateTime()));
        bug.setStatus(Bug.BugStatus.valueOf(rs.getString("status")));
        bug.setReportedBy(rs.getInt("reportedBy"));
        bug.setAssignedTo(getAssignedDeveloper(bug.getBugId()));
        return bug;
    }
    private int getAssignedDeveloper(int bugId) {
        String sql = "SELECT developerId FROM DeveloperBug WHERE bugId = ? ORDER BY assignedDate DESC LIMIT 1";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, bugId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("developerId");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0; // Return 0 if no developer is assigned
    }
}
