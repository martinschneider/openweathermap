package at.mschneider.weatherapp.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
	
	@Override
	@Query(value="select w from WeatherData w order by w.city.name asc, w.date desc")
	public List<WeatherData> findAll();
	
	/**
	 * Find stored weather data for a given city
	 * 
	 * @param city {@link City} to load the weather data for
	 * @return a {@link Collection} of {@link WeatherData} for the specified {@link City}
	 */
	WeatherData findByCityAndDate(City city, LocalDate date);
	
	@Query(value="delete from WeatherData w where w.city.id = ?1")
	@Modifying
	@Transactional
	void deleteByCityId(Long cityId);
}
