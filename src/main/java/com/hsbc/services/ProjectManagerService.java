package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Project;
import com.hsbc.beans.Employee;
import com.hsbc.exceptions.*;

import java.time.LocalDate;
import java.util.List;

public interface ProjectManagerService extends EmployeeService {
//    public List<Project> getManagedProjects(int managerId) throws EmployeeDoesNotExistException;
    public int createProject(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException;
    public void assignTeamMember(int projectId, int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException;
    public void closeBug(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException;
    public void assignBugToDeveloper(int bugId, int developerId) throws BugDoesNotExistException, EmployeeDoesNotExistException, BugAlreadyClosedException;
}
