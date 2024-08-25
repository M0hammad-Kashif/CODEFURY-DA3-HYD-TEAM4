package com.hsbc.services;

import com.hsbc.beans.*;
import com.hsbc.dao.BugDao;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;
import com.hsbc.utilities.BugDaoFactory;

import java.util.List;

public class TesterServiceImpl implements TesterService {

    //private ProjectDao projectDao;
    private BugDao bugDao;

    public TesterServiceImpl() {
        //this.projectDao = ProjectDaoFactory.getProjectDao();
        this.bugDao = BugDaoFactory.getBugDao();

    }

//    @Override
//    public List<Project> getAssignedProjects(int testerId) throws EmployeeDoesNotExistException {
//        return projectDao.findByTesterId(testerId);
//    }

    @Override
    public void reportBug(int projectId, String title, String description, String severityLevel, int reportedBy) throws ProjectDoesNotExistException {
        // Reporting a bug can involve adding the bug to the project's bug list
        bugDao.create( projectId,  title,  description,  severityLevel,  reportedBy);
    }
}
