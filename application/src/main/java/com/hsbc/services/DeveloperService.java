package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.exceptions.BugAlreadyClosedException;
import com.hsbc.exceptions.BugDoesNotExistException;
import com.hsbc.exceptions.EmployeeDoesNotExistException;

import java.util.List;

public interface DeveloperService {
//    public List<Bug> getAssignedBugs(int developerId) throws EmployeeDoesNotExistException;
    public void resolveBug(int bugId) throws BugDoesNotExistException, BugAlreadyClosedException;
}
