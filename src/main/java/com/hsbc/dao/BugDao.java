package com.hsbc.dao;

import com.hsbc.beans.Bug;

public interface BugDao {
    // create new bug - returns unique bug id
    public int create(int projectId, String title, String description, String severityLevel, int reportedBy);
    // assign bug to developer
    public void assignToDev(int bugId,int employeeId);
    // find bug by id
    public Bug findById(int bugId);
    // find bug by name
    public Bug findByName(String bugName);
    // change assigned Developer
    public void changeAssignedEmployee(int bugId,int employeeId);
    // resolve bug
    public void resolve(int bugId);
    // close bug
    public void close(int bugId);
}
