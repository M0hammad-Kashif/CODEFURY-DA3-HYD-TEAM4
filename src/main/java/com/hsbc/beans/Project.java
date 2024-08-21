package com.hsbc.beans;

import java.time.LocalDate;
import java.util.List;

public class Project {
    private int projectId;
    private String projectName;
    private LocalDate startDate;
    public enum ProjectStatus { inProgress, finished }
    private ProjectStatus status;
    private int managerId;
    private List<Integer> teamMembers; // list of employee ids of team members
    private List<Integer> bugs; // list of bug ids of bugs
    private static int count = 2000;

    // constructors
    public Project() {
    }

    public Project(int projectId, String projectName, ProjectStatus status, LocalDate startDate, int managerId, List<Integer> teamMembers, List<Integer> bugs) {
        this.projectId = generateProjectId();
        this.projectName = projectName;
        this.status = status;
        this.startDate = startDate;
        this.managerId = managerId;
        this.teamMembers = teamMembers;
        this.bugs = bugs;
    }

    // getters and setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManager(int managerId) {
        this.managerId = managerId;
    }

    public List<Integer> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<Integer> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public List<Integer> getBugs() {
        return bugs;
    }

    public void setBugs(List<Integer> bugs) {
        this.bugs = bugs;
    }

    // class methods
    private int generateProjectId(){
        return ++count;
    }
}
