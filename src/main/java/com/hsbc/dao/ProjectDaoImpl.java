package com.hsbc.dao;

import com.hsbc.beans.Project;

import java.time.LocalDate;

public class ProjectDaoImpl implements ProjectDao {

    @Override
    public int create(String projectName, LocalDate startDate, int managerId) {
        return 0;
    }

    @Override
    public void addTeamMember(int employeeId) {
        // add employee to existing list of team members
    }

    @Override
    public void removeTeamMember(int employeeId) {

    }

    @Override
    public void changeManager(int employeeId) {

    }

    @Override
    public void addBug(int bugId) {
        // add bug to existing list of bugs
    }

    @Override
    public Project findById(int projectId) {
        return null;
    }

    @Override
    public Project findByName(String name) {
        return null;
    }

    @Override
    public void finish(int projectId) {

    }
}
