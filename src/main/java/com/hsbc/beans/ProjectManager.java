package com.hsbc.beans;

import java.util.List;

public class ProjectManager extends Employee {
    private List<Project> managedProjects;

    // constructors
    public ProjectManager() {
    }

    public ProjectManager(int employeeId, String email, String name, Role role, List<Project> managedProjects) {
        super(employeeId, email, name, role);
        this.managedProjects = managedProjects;
    }

    // getters and setters
    public List<Project> getManagedProjects() {
        return managedProjects;
    }

    public void setManagedProjects(List<Project> managedProjects) {
        this.managedProjects = managedProjects;
    }
}
