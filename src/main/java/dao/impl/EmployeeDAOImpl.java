package dao.impl;

import dao.EmployeeDAO;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    @Override
    public void createEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return HibernateUtil.getSessionFactory().openSession().get(Employee.class, id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employees = new ArrayList<>(session.createQuery("FROM Employee", Employee.class).list());
            transaction.commit();
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, id);
            session.delete(employee);
            transaction.commit();
        }
    }
}
