package com.hsbc.dao;

import com.hsbc.beans.Bug;

public class BugDaoImpl implements BugDao {

    @Override
    public int create(int projectId, String title, String description, Bug.Level severityLevel, int reportedBy) {
        return 0;
    }

    @Override
    public void assignToDev(int employeeId) {

    }

    @Override
    public Bug findById(int bugId) {
        return null;
    }

    @Override
    public Bug findByName(String bugName) {
        return null;
    }

    @Override
    public void changeAssignedEmployee(int employeeId) {

    }

    @Override
    public void resolve(int bugId) {

    }

    @Override
    public void close(int bugId) {

    }
}
