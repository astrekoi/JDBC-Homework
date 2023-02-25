package dao;

import model.City;
import java.sql.SQLException;
import java.util.List;

public interface CityDAO {
    void createCity(City city) throws SQLException;
    City getCityById(Long id) throws SQLException;
    List<City> getAllCities() throws SQLException;
    void updateCity(City city) throws SQLException;
    void deleteCityById(Long id) throws SQLException;
}
