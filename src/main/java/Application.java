import dao.EmployeeDAO;
import dao.impl.EmployeeDAOImpl;
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
                final int id = resultSet.getInt("id");
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

            // Создаем нового сотрудника
            Employee employee = new Employee();
            employee.setFirstName("John");
            employee.setLastName("Doe");
            employee.setGender("Male");
            employee.setAge(30);
            employeeDAO.createEmployee(employee);

            // Получить сотрудника по ID
            Employee retrievedEmployee = employeeDAO.getEmployeeById(1L);

            // Обновить сотрудника
            retrievedEmployee.setAge(31);
            employeeDAO.updateEmployee(retrievedEmployee);

            // Удалить сотрудника по ID
            employeeDAO.deleteEmployeeById(1L);

            // Получить всех сотрудников
            List<Employee> employees = employeeDAO.getAllEmployees();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
