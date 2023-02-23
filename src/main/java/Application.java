import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();

        Employee employee = new Employee();
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setGender("Male");
        employee.setAge(30);
        employee.setCityId(1);
        employeeDAO.createEmployee(employee);

        Employee retrievedEmployee = employeeDAO.getEmployeeById(1L);
        System.out.println("Retrieved employee: " + retrievedEmployee);

        List<Employee> employees = employeeDAO.getAllEmployees();
        System.out.println("All employees: " + employees);

        Employee employeeToUpdate = employeeDAO.getEmployeeById(1L);
        employeeToUpdate.setFirstName("Jane");
        employeeDAO.updateEmployee(employeeToUpdate);

        employeeDAO.deleteEmployeeById(1L);
    }
}
