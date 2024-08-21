package com.hsbc.beans;

import java.util.List;

public class Developer extends Employee {
    private Project assignedProject;
    private List<Bug> assignedBugs;

    // constructors
    public Developer() {
    }

    public Developer(int employeeId, String email, String name, Role role, Project assignedProject, List<Bug> assignedBugs) {
        super(employeeId, email, name, role);
        this.assignedProject = assignedProject;
        this.assignedBugs = assignedBugs;
    }

    // getters and setters
    public Project getAssignedProject() {
        return assignedProject;
    }

    public void setAssignedProject(Project assignedProject) {
        this.assignedProject = assignedProject;
    }

    public List<Bug> getAssignedBugs() {
        return assignedBugs;
    }

    public void setAssignedBugs(List<Bug> assignedBugs) {
        this.assignedBugs = assignedBugs;
    }

    // developer methods
    public void resolveBug(int bugId) {
        // find the bug in the list of assigned bugs
        for(Bug assignedBug: assignedBugs){
            if(assignedBug.getBugId() == bugId){
                // when found, set the status of the bug to "resolved"
                assignedBug.setStatus(Bug.BugStatus.resolved);
            }
        }
    }
}
