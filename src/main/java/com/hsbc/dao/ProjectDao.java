package com.hsbc.dao;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Project;
import com.hsbc.exceptions.EmployeeAlreadyAddedToProjectException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.exceptions.ProjectDoesNotExistException;

import java.time.LocalDate;

public interface ProjectDao {
    // create a new project - returns unique project id
    public int create(String projectName, LocalDate startDate, int managerId) throws EmployeeDoesNotExistException;

    // add a team member to project
    public void addTeamMember(int projectId,int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException;

    // remove a team member from project
    public void removeTeamMember(int projectId,int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException;

    // change manager of project
    public void changeManager(int projectId,int employeeId) throws ProjectDoesNotExistException, EmployeeDoesNotExistException, EmployeeAlreadyAddedToProjectException;

    // find project by id
    public Project findById(int projectId) throws ProjectDoesNotExistException;

    // find project by name
    public Project findByName(String name) throws ProjectDoesNotExistException;

    // end project
    public void finish(int projectId) throws ProjectDoesNotExistException;
}
