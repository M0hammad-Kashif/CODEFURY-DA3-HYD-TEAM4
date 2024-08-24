package com.hsbc.dao;

import com.hsbc.beans.Employee;

public interface EmployeeDao {
    // create new employee
    public int create(String name, String email, String role);

    // find employee by id
    public Employee findById(int employeeId);

    // update employee email
    public void updateEmail(int employeeId, String email);

    // update employee role
    public void updateRole(int employeeId, String role);

    // remove employee
    public void remove(int employeeId);
}
