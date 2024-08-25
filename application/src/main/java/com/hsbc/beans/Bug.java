package com.hsbc.beans;

import java.time.LocalDate;

public class Bug {
    private int bugId;
    private int projectId;
    private String title;
    private String description;
    public enum Level { low, medium, high }
    private Level severityLevel;
    private LocalDate reportedOn;
    public enum BugStatus { created, resolved, closed }
    private BugStatus status;
    private int reportedBy; // employee id of tester who reported the bug
    private int assignedTo; // employee id of developer which the bug is assigned to
    private static int count = 1000;

    // constructors
    public Bug() {
    }

    public Bug(int bugId, int projectId, String title, String description, Level severityLevel, LocalDate reportedOn, int reportedBy, BugStatus status, int assignedTo) {
        this.bugId = generateBugId();
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.severityLevel = severityLevel;
        this.reportedOn = reportedOn;
        this.reportedBy = reportedBy;
        this.status = status;
        this.assignedTo = assignedTo;
    }

    // getters and setters
    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Level getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Level severityLevel) {
        this.severityLevel = severityLevel;
    }

    public LocalDate getReportedOn() {
        return reportedOn;
    }

    public void setReportedOn(LocalDate reportedOn) {
        this.reportedOn = reportedOn;
    }

    public int getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(int reportedBy) {
        this.reportedBy = reportedBy;
    }

    public BugStatus getStatus() {
        return status;
    }

    public void setStatus(BugStatus status) {
        this.status = status;
    }

    public int getAssignedTo() {
        return this.assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    // class methods
    private int generateBugId(){
        return ++count;
    }
}
