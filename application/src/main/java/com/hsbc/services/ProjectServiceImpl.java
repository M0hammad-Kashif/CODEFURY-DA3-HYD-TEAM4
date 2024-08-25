package com.hsbc.services;

import com.hsbc.beans.Project;
import com.hsbc.dao.ProjectDao;
import com.hsbc.exceptions.EmployeeAlreadyAddedToProjectException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;
import com.hsbc.utilities.ProjectDaoFactory;

import java.time.LocalDate;

public class ProjectServiceImpl implements ProjectService {

    private ProjectDao projectDao;

    public ProjectServiceImpl() {
        this.projectDao = ProjectDaoFactory.getProjectDao();
    }

    @Override
    public int createProject(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException {
        return projectDao.create(projectName, startDate, managerId);
    }

    @Override
    public Project getProjectById(int projectId) throws ProjectDoesNotExistException {
        return projectDao.findById(projectId);
    }

    @Override
    public void addTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException {
        projectDao.addTeamMember(projectId, employeeId);
    }

    @Override
    public void removeTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException {
        projectDao.removeTeamMember(projectId, employeeId);
    }
}
