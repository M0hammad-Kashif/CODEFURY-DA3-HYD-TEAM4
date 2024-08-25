package com.hsbc.utilities;

import com.hsbc.dao.EmployeeDao;
import com.hsbc.dao.EmployeeDaoImpl;

public class EmployeeDaoFactory {
    public static EmployeeDao getEmployeeDao(){
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        return employeeDao;
    }
}
