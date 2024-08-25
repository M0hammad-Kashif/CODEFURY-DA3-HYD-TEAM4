package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Project;
import com.hsbc.dao.BugDao;
import com.hsbc.dao.ProjectDao;
import com.hsbc.exceptions.*;
import com.hsbc.utilities.BugDaoFactory;
import com.hsbc.utilities.ProjectDaoFactory;

import java.time.LocalDate;
import java.util.List;

public class ProjectManagerServiceImpl extends EmployeeServiceImpl implements ProjectManagerService {

    private ProjectDao projectDao;
    private BugDao bugDao;

    public ProjectManagerServiceImpl() {
        this.projectDao = ProjectDaoFactory.getProjectDao();
        this.bugDao = BugDaoFactory.getBugDao();
    }

//    @Override
//    public List<Project> getManagedProjects(int managerId) throws EmployeeDoesNotExistException {
//        return projectDao.findByManagerId(managerId);
//    }

    @Override
    public int createProject(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException {
        return projectDao.create(projectName, startDate, managerId);
    }

    @Override
    public void assignTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException {
        projectDao.addTeamMember(projectId, employeeId);
    }

    @Override
    public void closeBug(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException {
        bugDao.close(bugId);
    }

    @Override
    public void assignBugToDeveloper(int bugId, int developerId) throws BugDoesNotExistException, EmployeeDoesNotExistException, BugAlreadyClosedException {
        bugDao.assignToDev(bugId, developerId);
    }
}
