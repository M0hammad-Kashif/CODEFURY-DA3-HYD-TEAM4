package com.hsbc.testing;

import com.hsbc.beans.Project;
import com.hsbc.dao.ProjectDao;
import com.hsbc.exceptions.EmployeeAlreadyAddedToProjectException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;
import com.hsbc.services.ProjectServiceImpl;
import com.hsbc.utilities.ProjectDaoFactory;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ProjectServiceImplTest {

    private ProjectServiceImpl projectService;
    private ProjectDao projectDao;

    @Before
    public void setUp() {
        projectDao = ProjectDaoFactory.getProjectDao(); // In-memory DAO
        projectService = new ProjectServiceImpl();
    }

    @Test
    public void testCreateProject() {
        int projectId = 0;
        try {
            projectId = projectService.createProject("Project Name", LocalDate.now(), 101);
        } catch (EmployeeDoesNotExistException e) {
            fail("Employee should exist but was not found.");
        }

        Project project = null;
        try {
            project = projectService.getProjectById(projectId);
        } catch (ProjectDoesNotExistException e) {
            fail("Project should exist but was not found.");
        }
        assertNotNull(project);
        assertEquals("Project Name", project.getProjectName());
    }

    @Test
    public void testAddTeamMember() {
        int projectId = 0;
        try {
            projectId = projectService.createProject("Project Name", LocalDate.now(), 101);
            projectService.addTeamMember(projectId, 102);
        } catch (ProjectDoesNotExistException | EmployeeDoesNotExistException | EmployeeAlreadyAddedToProjectException e) {
            fail("Exception should not have been thrown");
        }

        Project project = null;
        try {
            project = projectService.getProjectById(projectId);
        } catch (ProjectDoesNotExistException e) {
            fail("Project should exist but was not found.");
        }
        assertNotNull(project);
        assertEquals(1, project.getTeamMembers().size());
    }

    @Test
    public void testAddTeamMemberThrowsException() {
        try {
            projectService.addTeamMember(999, 101); // Assuming 999 is an ID that doesn't exist
            fail("Expected ProjectDoesNotExistException to be thrown");
        } catch (ProjectDoesNotExistException e) {
            // Test passes if exception is thrown
        } catch (EmployeeDoesNotExistException | EmployeeAlreadyAddedToProjectException e) {
            fail("Expected ProjectDoesNotExistException, but got a different exception instead");
        }
    }

    @Test
    public void testGetProjectById() {
        int projectId = 0;
        try {
            projectId = projectService.createProject("Project Name", LocalDate.now(), 101);
        } catch (EmployeeDoesNotExistException e) {
            fail("Employee should exist but was not found.");
        }

        Project result = null;
        try {
            result = projectService.getProjectById(projectId);
        } catch (ProjectDoesNotExistException e) {
            fail("Project should exist but was not found.");
        }

        assertNotNull(result);
        assertEquals("Project Name", result.getProjectName());
    }
}