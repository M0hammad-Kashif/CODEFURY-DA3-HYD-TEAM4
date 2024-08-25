package com.hsbc.testing;
import com.hsbc.beans.Employee;
import com.hsbc.dao.EmployeeDao;
import com.hsbc.exceptions.EmployeeDoesNotExistException;
import com.hsbc.services.EmployeeServiceImpl;
import com.hsbc.utilities.EmployeeDaoFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;
    private EmployeeDao mockEmployeeDao;

    @Before
    public void setUp() {
        mockEmployeeDao = EmployeeDaoFactory.getEmployeeDao();
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void testCreateEmployee() {
        int employeeId = employeeService.createEmployee("John Doe", "john.doe@example.com", "Developer");
        assertEquals(1, employeeId);
    }

    @Test
    public void testGetEmployeeById() throws EmployeeDoesNotExistException {
        int employeeId = employeeService.createEmployee("Jane Doe", "jane.doe@example.com", "Manager");
        Employee employee = employeeService.getEmployeeById(employeeId);
        assertEquals("Jane Doe", employee.getName());
        assertEquals("jane.doe@example.com", employee.getEmail());
        assertEquals("Manager", employee.getRole());
    }

    @Test
    public void testUpdateEmployeeEmail() throws EmployeeDoesNotExistException {
        int employeeId = employeeService.createEmployee("John Smith", "john.smith@example.com", "Developer");
        employeeService.updateEmployeeEmail(employeeId, "john.smith@newdomain.com");
        Employee employee = employeeService.getEmployeeById(employeeId);
        assertEquals("john.smith@newdomain.com", employee.getEmail());
    }

    @Test
    public void testUpdateEmployeeRole() throws EmployeeDoesNotExistException {
        int employeeId = employeeService.createEmployee("Emily Johnson", "emily.johnson@example.com", "Analyst");
        employeeService.updateEmployeeRole(employeeId, "Senior Analyst");
        Employee employee = employeeService.getEmployeeById(employeeId);
        assertEquals("Senior Analyst", employee.getRole());
    }


}
