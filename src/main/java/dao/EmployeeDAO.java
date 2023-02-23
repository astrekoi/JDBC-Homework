package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void createEmployee(Employee employee) throws SQLException;
    Employee getEmployeeById(Long id) throws SQLException;
    List<Employee> getAllEmployees() throws SQLException;
    void updateEmployee(Employee employee) throws SQLException;
    void deleteEmployeeById(Long id) throws SQLException;
}
