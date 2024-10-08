package com.hsbc.beans;

import java.util.List;

public class Tester extends Employee {
    private List<Project> assignedProjects;

    // constructors
    public Tester() {
    }

    public Tester(int employeeId, String email, String name, Role role, List<Project> assignedProjects) {
        super(employeeId, email, name, role);
        this.assignedProjects = assignedProjects;
    }

    // getters and setters
    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }
}
