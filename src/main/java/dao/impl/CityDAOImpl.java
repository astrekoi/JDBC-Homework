package dao;

import model.City;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;

public class CityDAOImpl implements CityDAO {

private final EntityManagerFactory entityManagerFactory;

public CityDAOImpl() {
    entityManagerFactory = HibernateUtil.getEntityManagerFactory();
}

@Override
public void createCity(City city) throws SQLException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    try {
        transaction.begin();
        entityManager.persist(city);
        entityManager.flush();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        throw new SQLException("Failed to create city", e);
    } finally {
        entityManager.close();
    }
}

@Override
public City getCityById(Long id) throws SQLException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        return entityManager.find(City.class, id);
    } catch (Exception e) {
        throw new SQLException("Failed to get city by id", e);
    } finally {
        entityManager.close();
    }
}

@Override
public List<City> getAllCities() throws SQLException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    try {
        Query query = entityManager.createQuery("SELECT c FROM City c");
        return query.getResultList();
    } catch (Exception e) {
        throw new SQLException("Failed to get all cities", e);
    } finally {
        entityManager.close();
    }
}

@Override
public void updateCity(City city) throws SQLException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    try {
        transaction.begin();
        entityManager.merge(city);
        entityManager.flush();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        throw new SQLException("Failed to update city", e);
    } finally {
        entityManager.close();
    }
}

@Override
public void deleteCityById(Long id) throws SQLException {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();
    try {
        transaction.begin();
        City city = entityManager.find(City.class, id);
        entityManager.remove(city);
        entityManager.flush();
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null && transaction.isActive()) {
            transaction.rollback();
        }
        throw new SQLException("Failed to delete city by id", e);
    } finally {
        entityManager.close();
    }
}
}
