package com.hsbc.dao;

import com.hsbc.beans.Project;
import com.hsbc.utilities.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public int create(String projectName, LocalDate startDate, int managerId) {
        String sql = "INSERT INTO Project (projectName, startDate, status, managerId) VALUES (?, ?, 'inProgress', ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, projectName);
            pstmt.setDate(2, Date.valueOf(startDate));
            pstmt.setInt(3, managerId);
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
    public void addTeamMember(int projectId, int employeeId) {
        String sql = "INSERT INTO ProjectTeamMember (projectId, employeeId, assignedDate) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, employeeId);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTeamMember(int projectId, int employeeId) {
        String sql = "DELETE FROM ProjectTeamMember WHERE projectId = ? AND employeeId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeManager(int projectId, int managerId) {
        String sql = "UPDATE Project SET managerId = ? WHERE projectId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, projectId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBug(int projectId, int bugId) {
        String sql = "UPDATE Bug SET projectId = ? WHERE bugId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, bugId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findById(int projectId) {
        String sql = "SELECT * FROM Project WHERE projectId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractProjectFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Project findByName(String name) {
        String sql = "SELECT * FROM Project WHERE projectName = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractProjectFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void finish(int projectId) {
        String sql = "UPDATE Project SET status = 'finished' WHERE projectId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    private List<Integer> getTeamMembers(int projectId) {
        List<Integer> teamMembers = new ArrayList<>();
        String sql = "SELECT employeeId FROM ProjectTeamMember WHERE projectId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    teamMembers.add(rs.getInt("employeeId"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return teamMembers;
    }

    private List<Integer> getBugs(int projectId) {
        List<Integer> bugs = new ArrayList<>();
        String sql = "SELECT bugId FROM Bug WHERE projectId = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, projectId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bugs.add(rs.getInt("bugId"));
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bugs;
    }


    private Project extractProjectFromResultSet(ResultSet rs) throws SQLException {
        Project project = new Project();
        project.setProjectId(rs.getInt("projectId"));
        project.setProjectName(rs.getString("projectName"));
        project.setStartDate(rs.getDate("startDate").toLocalDate());
        project.setStatus(Project.ProjectStatus.valueOf(rs.getString("status")));
        project.setManagerId(rs.getInt("managerId"));
        project.setTeamMembers(getTeamMembers(project.getProjectId()));
        project.setBugs(getBugs(project.getProjectId()));
        return project;
    }
}


