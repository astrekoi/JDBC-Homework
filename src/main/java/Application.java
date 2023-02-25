import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) throws Exception {

        // создание объектов City
        City city1 = new City();
        city1.setName("New York");
        List<Employee> employees1 = new ArrayList<>();

        City city2 = new City();
        city2.setName("London");
        List<Employee> employees2 = new ArrayList<>();

        // создание объектов Employee
        Employee employee1 = new Employee();
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setGender("Male");
        employee1.setAge(30);
        employee1.setCity(city1);
        employees1.add(employee1);

        Employee employee2 = new Employee();
        employee2.setFirstName("Jane");
        employee2.setLastName("Doe");
        employee2.setGender("Female");
        employee2.setAge(25);
        employee2.setCity(city1);
        employees1.add(employee2);

        Employee employee3 = new Employee();
        employee3.setFirstName("Bob");
        employee3.setLastName("Smith");
        employee3.setGender("Male");
        employee3.setAge(40);
        employee3.setCity(city2);
        employees2.add(employee3);

        Employee employee4 = new Employee();
        employee4.setFirstName("Alice");
        employee4.setLastName("Smith");
        employee4.setGender("Female");
        employee4.setAge(35);
        employee4.setCity(city2);
        employees2.add(employee4);

        city1.setEmployees(employees1);
        city2.setEmployees(employees2);

        // создание DAO объектов
        CityDAO cityDAO = new CityDAOImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        try {
            // создание городов и сотрудников
            cityDAO.createCity(city1);
            cityDAO.createCity(city2);
            employeeDAO.createEmployee(employee1);
            employeeDAO.createEmployee(employee2);
            employeeDAO.createEmployee(employee3);
            employeeDAO.createEmployee(employee4);

            // получение города по id
            City cityById = cityDAO.getCityById(city1.getId());
            System.out.println("City by ID: " + cityById);

            // получение всех городов
            List<City> allCities = cityDAO.getAllCities();
            System.out.println("All cities: " + allCities);

            // обновление города
            city1.setName("New York City");
            cityDAO.updateCity(city1);

            // удаление города по id
            cityDAO.deleteCityById(city2.getId());

            // получение сотрудника по id
            Employee employeeById = employeeDAO.getEmployeeById(employee1.getId());
            System.out.println("Employee by ID: " + employeeById);

            // получение всех сотрудников
            List<Employee> allEmployees = employeeDAO.getAllEmployees();
            System.out.println("All employees: " + allEmployees);

            // обновление сотрудника
            employee1.setFirstName("Johnny");
            employeeDAO.updateEmployee(employee1);

            // удаление сотрудника по id
            employeeDAO.deleteEmployeeById(employee2.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
