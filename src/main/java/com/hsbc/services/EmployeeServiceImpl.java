package com.hsbc.services;

import com.hsbc.beans.Employee;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.utilities.EmployeeDaoFactory;
import com.hsbc.services.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl() {
        this.employeeDao = EmployeeDaoFactory.getEmployeeDao();
    }

    @Override
    public int createEmployee(String name, String email, String role) {
        return employeeDao.create(name, email, role);
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeDoesNotExistException {
        return employeeDao.findById(employeeId);
    }

    @Override
    public void updateEmployeeEmail(int employeeId, String email) throws EmployeeDoesNotExistException {
        employeeDao.updateEmail(employeeId, email);
    }

    @Override
    public void updateEmployeeRole(int employeeId, String role) throws EmployeeDoesNotExistException {
        employeeDao.updateRole(employeeId, role);
    }

    @Override
    public void deleteEmployee(int employeeId) throws EmployeeDoesNotExistException {
        employeeDao.remove(employeeId);
    }
}
