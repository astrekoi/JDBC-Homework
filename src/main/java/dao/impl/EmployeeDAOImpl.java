package dao.impl;

import dao.EmployeeDAO;
import jbdc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String FIND_ALL = "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id";
    private static final String INSERT = "INSERT INTO employee (id, first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM employee INNER JOIN city ON employee.city_id=city.city_id AND employee.id=?";
    private static final String UPDATE = "UPDATE employee SET first_name=? WHERE id=?";
    private static final String DELETE = "DELETE FROM employee WHERE id=?";

    @Override
    public void createEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setLong(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setLong(6, employee.getCityId().getId());
            statement.executeUpdate();
        }
    }

    @Override
    public Employee getEmployeeById(Long id) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createEmployeeFromResultSet(resultSet);
            } else {
                return null;
            }
        }
    }

    @Override
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                employees.add(createEmployeeFromResultSet(resultSet));
            }
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, employee.getFirstName());
            statement.setLong(2, employee.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Employee createEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        Integer age = resultSet.getInt("age");
        Long cityId = resultSet.getLong("city_id");
        String cityName = resultSet.getString("city_name");
        City city = new City(cityId, cityName);
        return new Employee(id, firstName, lastName, gender, age, city);
    }
}