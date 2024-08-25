package com.hsbc.services;

import com.hsbc.beans.Project;
import com.hsbc.exceptions.EmployeeAlreadyAddedToProjectException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;

import java.time.LocalDate;
import java.util.List;

public interface ProjectService {
    public int createProject(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException;
    public Project getProjectById(int projectId) throws ProjectDoesNotExistException;
    public void addTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException; ;
    public void removeTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException;
}
