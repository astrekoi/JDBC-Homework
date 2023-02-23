import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
import jbdc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.List;

public class Application {

    public static void main(String[] args) throws SQLException {


        // Создаем переменные с данными для подключения к базе
        final String user = System.getenv("DB_USER");
        final String password = System.getenv("DB_PASSWORD");
        final String url = System.getenv("DB_URL");

        // Создаем соединение с базой с помощью Connection
        // Формируем запрос к базе с помощью PreparedStatement
        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee")) {

            // Делаем запрос к базе и результат кладем в ResultSet
            final ResultSet resultSet = statement.executeQuery();

            // Методом next проверяем есть ли следующий элемент в resultSet
            // и одновременно переходим к нему, если таковой есть
            while (resultSet.next()) {
                // Получаем значения полей из текущей строки resultSet
                final Long id = resultSet.getLong("id");
                final String firstName = resultSet.getString("first_name");
                final String lastName = resultSet.getString("last_name");
                final String gender = resultSet.getString("gender");
                final int age = resultSet.getInt("age");

                // Выводим данные в консоль
                System.out.println("ID: " + id);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Gender: " + gender);
                System.out.println("Age: " + age);
                System.out.println();
            }

            EmployeeDAO employeeDAO = new EmployeeDAOImpl();

            // Создаем тестового сотрудника
            Employee employee = new Employee(2002L, "John", "Doe", "Male", 30, new City((5L),"Солнечный"));

            // Добавляем сотрудника в базу данных
            employeeDAO.createEmployee(employee);

            // Получаем добавленного сотрудника по id
            Employee foundEmployee = employeeDAO.getEmployeeById(employee.getId());
            System.out.println("Found employee: " + foundEmployee);

            // Получаем всех сотрудников
            List<Employee> allEmployees = employeeDAO.getAllEmployees();
            System.out.println("All employees: " + allEmployees);

            // Обновляем имя сотрудника
            foundEmployee.setFirstName("Mike");
            employeeDAO.updateEmployee(foundEmployee);

            // Получаем обновленного сотрудника по id
            foundEmployee = employeeDAO.getEmployeeById(employee.getId());
            System.out.println("Updated employee: " + foundEmployee);

            // Удаляем сотрудника по id
            employeeDAO.deleteEmployeeById(employee.getId());

            // Проверяем, что сотрудника больше нет в базе данных
            foundEmployee = employeeDAO.getEmployeeById(employee.getId());
            System.out.println("Found employee after delete: " + foundEmployee);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
