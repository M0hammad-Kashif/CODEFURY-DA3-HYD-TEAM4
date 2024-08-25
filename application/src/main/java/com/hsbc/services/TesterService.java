package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Project;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;

import java.util.List;

public interface TesterService {
//    public List<Project> getAssignedProjects(int testerId) throws EmployeeDoesNotExistException;
    public void reportBug(int projectId, String title, String description, String severityLevel, int reportedBy) throws ProjectDoesNotExistException;
}
