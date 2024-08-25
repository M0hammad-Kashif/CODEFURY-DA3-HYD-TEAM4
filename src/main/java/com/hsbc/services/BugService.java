package com.hsbc.services;

import com.hsbc.beans.Bug;
import com.hsbc.exceptions.BugDoesNotExistException;

public interface BugService {
    public int createBug(int projectId, String title, String description, String severityLevel, int reportedBy);
    public Bug getBugById(int bugId) throws BugDoesNotExistException;
    public Bug getBugByName(String bugName) throws BugDoesNotExistException;
    public void closeBug(int bugId) throws BugDoesNotExistException;
}
