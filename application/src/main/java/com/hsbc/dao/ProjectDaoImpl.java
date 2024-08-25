package com.hsbc.dao;

import com.hsbc.beans.Project;
import com.hsbc.exceptions.EmployeeAlreadyAddedToProjectException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;
import com.hsbc.utilities.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public int create(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException {
        // check if manager exists
        EmployeeDaoImpl emp = new EmployeeDaoImpl();
        emp.findById(managerId);

        // prepare the SQL command
        String sql = "INSERT INTO Project (projectName, startDate, status, managerId) VALUES (?, ?, 'inProgress', ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            // set all values in the sql statement and execute
            pstmt.setString(1, projectName);
            pstmt.setDate(2, Date.valueOf(startDate));
            pstmt.setInt(3, managerId);
            pstmt.executeUpdate();

            // go through the resultSet and return the unique project ID
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
    public void addTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException {
        // check if project exists
        findById(projectId);
        // check if employee exists
        EmployeeDaoImpl emp = new EmployeeDaoImpl();
        emp.findById(employeeId);
        // check if team member already added
        if(teamMemberExists(projectId, employeeId)){
            throw new EmployeeAlreadyAddedToProjectException("This member is already added to project!");
        }

        // prepare the SQL command
        String sql = "INSERT INTO ProjectTeamMember (projectId, employeeId, assignedDate) VALUES (?, ?, ?)";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set all values in the sql statement and execute
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, employeeId);
            pstmt.setDate(3, Date.valueOf(LocalDate.now()));
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException {
        // check if project exists
        findById(projectId);
        // check if employee exists
        EmployeeDaoImpl emp = new EmployeeDaoImpl();
        emp.findById(employeeId);

        // prepare the SQL command
        String sql = "DELETE FROM ProjectTeamMember WHERE projectId = ? AND employeeId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            // set all values in the sql statement and execute
            pstmt.setInt(1, projectId);
            pstmt.setInt(2, employeeId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeManager(int projectId, int managerId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException {
        // check if project exists
        findById(projectId);
        // check if manager exists
        EmployeeDaoImpl emp = new EmployeeDaoImpl();
        emp.findById(managerId);
        // check if manager is the same as existing
        if(teamMemberExists(projectId, managerId)){
            throw new EmployeeAlreadyAddedToProjectException("The manager already manages this project!");
        }

        // prepare the SQL commands
        String sql = "UPDATE Project SET managerId = ? WHERE projectId = ?";
        String sql2 = "UPDATE ProjectTeamMember SET employeeId = ?, assignedDate = ?, WHERE projectId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        ) {
            // set all values in the sql statements and execute
            pstmt.setInt(1, managerId);
            pstmt.setInt(2, projectId);
            pstmt.executeUpdate();

            pstmt2.setInt(1, managerId);
            pstmt2.setDate(2, Date.valueOf(LocalDate.now()));
            pstmt2.setInt(3, projectId);
            pstmt2.executeUpdate();


        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findById(int projectId) throws ProjectDoesNotExistException {
        // prepare the SQL command
        String sql = "SELECT * FROM Project WHERE projectId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values in the sql statement and execute
            pstmt.setInt(1, projectId);

            // execution returns a project row, extract it and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractProjectFromResultSet(rs);
                }
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new ProjectDoesNotExistException("Project with this id does not exist!");
    }

    @Override
    public Project findByName(String name) throws ProjectDoesNotExistException {
        // prepare the SQL command
        String sql = "SELECT * FROM Project WHERE projectName = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values in the sql statement and execute
            pstmt.setString(1, name);

            // execution returns a project row, extract it and return
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractProjectFromResultSet(rs);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        throw new ProjectDoesNotExistException("Project with this name does not exist!");
    }

    @Override
    public void finish(int projectId) throws ProjectDoesNotExistException {
        // check if project exists
        findById(projectId);

        // prepare the SQL command
        String sql = "UPDATE Project SET status = 'finished' WHERE projectId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            // set values in the sql statement and execute
            pstmt.setInt(1, projectId);
            pstmt.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // method to get a list of employees in a project
    private List<Integer> getTeamMembers(int projectId) {
        List<Integer> teamMembers = null;

        // create sql command
        String sql = "SELECT employeeId FROM ProjectTeamMember WHERE projectId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, projectId);

            // execution returns a list of employee ids
            teamMembers = new ArrayList<>();
            // traverse resultSet and add to list
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

    // method to get a list of bugs related to a project
    private List<Integer> getBugs(int projectId) {
        List<Integer> bugs = null;

        // create sql command
        String sql = "SELECT bugId FROM Bug WHERE projectId = ?";

        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, projectId);

            // execution returns a list of bug ids
            bugs = new ArrayList<>();
            // traverse resultSet and add to list
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

    // method to create a Project object, set all values and return it
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

    // method to check if team member already added to project
    private boolean teamMemberExists(int projectId, int employeeId) {
        try {
            // find the project
            Project project = findById(projectId);
            // iterate and find if the employee already exists
            for(int id: project.getTeamMembers()){
                if(id == employeeId) return true;
            }

        } catch (ProjectDoesNotExistException e) {
            e.printStackTrace();
        }

        return false;
    }
}


