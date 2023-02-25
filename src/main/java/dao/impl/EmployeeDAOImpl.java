package dao.impl;

import dao.EmployeeDAO;
import model.City;
import model.Employee;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final EntityManagerFactory emf;

    public EmployeeDAOImpl() throws Exception {
        emf = HibernateUtil.getEntityManagerFactory();
    }

    @Override
    public void createEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            City city = em.merge(employee.getCity());
            employee.setCity(city);
            em.persist(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
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
            List<Employee> employees = em.createQuery("SELECT e FROM Employee e LEFT JOIN FETCH e.city", Employee.class)
                    .getResultList();
            return employees;
        } finally {
            em.close();
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            City city = em.merge(employee.getCity());
            employee.setCity(city);
            em.merge(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteEmployeeById(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Employee employee = em.find(Employee.class, id);
            em.remove(employee);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
