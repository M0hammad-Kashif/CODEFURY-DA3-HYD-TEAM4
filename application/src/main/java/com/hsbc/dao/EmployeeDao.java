package com.hsbc.dao;

import com.hsbc.beans.Employee;
import com.hsbc.exceptions.EmployeeDoesNotExistException;

public interface EmployeeDao {
    // create new employee
    public int create(String name, String email, String role);

    // find employee by id
    public Employee findById(int employeeId) throws EmployeeDoesNotExistException;

    // update employee email
    public void updateEmail(int employeeId, String email) throws EmployeeDoesNotExistException;

    // update employee role
    public void updateRole(int employeeId, String role) throws EmployeeDoesNotExistException;

    // remove employee
    public void remove(int employeeId) throws EmployeeDoesNotExistException;
}
