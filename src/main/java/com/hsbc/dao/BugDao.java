package com.hsbc.dao;

import com.hsbc.beans.Bug;
import com.hsbc.exceptions.BugAlreadyClosedException;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;

public interface BugDao {
    // create new bug - returns unique bug id
    public int create(int projectId, String title, String description, String severityLevel, int reportedBy);

    // assign bug to developer
    public void assignToDev(int bugId,int employeeId) throws EmployeeDoesNotExistException, BugDoesNotExistException, BugAlreadyClosedException;

    // find bug by id
    public Bug findById(int bugId) throws BugDoesNotExistException;

    // find bug by name
    public Bug findByName(String bugName) throws BugDoesNotExistException;

    // change assigned Developer
    public void changeAssignedEmployee(int bugId,int employeeId) throws BugDoesNotExistException, BugAlreadyClosedException, EmployeeDoesNotExistException;

    // resolve bug
    public void resolve(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException;

    // close bug
    public void close(int bugId) throws BugDoesNotExistException;
}
