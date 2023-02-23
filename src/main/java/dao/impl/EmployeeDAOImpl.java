package dao.impl;

import dao.EmployeeDAO;
import jakarta.persistence.*;
import model.Employee;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private EntityManagerFactory emf;

    public EmployeeDAOImpl() throws Exception {
        setUp();
    }

    protected void setUp() throws Exception {
        emf = Persistence.createEntityManagerFactory( "myPersistenceUnit" );
    }

    @Override
    public void createEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Employee employee = em.find(Employee.class, id);
            return employee;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e", Employee.class)
                    .getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.remove(employee);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
