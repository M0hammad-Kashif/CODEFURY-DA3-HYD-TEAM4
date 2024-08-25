package com.hsbc.services;

import com.hsbc.beans.Employee;
import com.hsbc.exceptions.EmployeeDoesNotExistException;

import java.util.List;

public interface EmployeeService {
    int createEmployee(String name, String email, String role);
    Employee getEmployeeById(int employeeId) throws EmployeeDoesNotExistException;
    void updateEmployeeEmail(int employeeId, String email) throws EmployeeDoesNotExistException;
    void updateEmployeeRole(int employeeId, String role) throws EmployeeDoesNotExistException;
    void deleteEmployee(int employeeId) throws EmployeeDoesNotExistException;
}
