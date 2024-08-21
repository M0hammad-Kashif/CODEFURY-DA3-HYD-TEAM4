package com.hsbc.beans;

public class Employee {
    private int employeeId;
    private String name;
    private String email;
    public enum Role { developer, tester, projectManager}
    private Role role;

    // constructors
    public Employee() {
    }

    public Employee(int employeeId, String email, String name, Role role) {
        this.employeeId = employeeId;
        this.email = email;
        this.name = name;
        this.role = role;
    }

    // getters and setters
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
