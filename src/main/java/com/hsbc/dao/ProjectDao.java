package com.hsbc.dao;

import com.hsbc.beans.Bug;
import com.hsbc.beans.Project;

import java.time.LocalDate;

public interface ProjectDao {
    // create a new project - returns unique project id
    public int create(String projectName, LocalDate startDate, int managerId);
    // add a team member to project
    public void addTeamMember(int projectId,int employeeId);
    // remove a team member from project
    public void removeTeamMember(int projectId,int employeeId);
    // change manager of project
    public void changeManager(int projectId,int employeeId);
    // add a bug to project
    public void addBug(int projectId,int bugId);
    // find project by id
    public Project findById(int projectId);
    // find project by name
    public Project findByName(String name);
    // end project
    public void finish(int projectId);
}
