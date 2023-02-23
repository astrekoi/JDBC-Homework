package dao;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    public void createEmployee(Employee employee);
    public Employee getEmployeeById(Long id);
    public List<Employee> getAllEmployees();
    public void updateEmployee(Employee employee);
    public void deleteEmployeeById(Long id);
}

