package at.mschneider.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import at.mschneider.weatherapp.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {	
	
	City findByName(String cityName);
}
