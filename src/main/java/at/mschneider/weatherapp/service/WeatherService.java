package at.mschneider.weatherapp.service;

import java.util.List;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;

/**
 * Service to import weather data from an external source
 */
public interface WeatherService {
	/**
	 * @param city
	 *            the city for which the weather data should be imported
	 * @return {@link WeatherData}
	 */
	WeatherData importWeatherData(City city);

	/**
	 * @param weather
	 *            {@link List} of {@link City}s for which the weather data
	 *            should be imported
	 * @return {@link List} of {@link WeatherData} with imported weather data
	 */
	WeatherData importWeatherData(List<City> cities);

	/**
	 * @param weather
	 *            the weather data which should be updated
	 * @return {@link WeatherData} with updated values
	 */
	WeatherData updateWeatherData(WeatherData weather);

	/**
	 * @param weather
	 *            {@link List} of {@link WeatherData} which should be updated
	 * @return {@link List} of {@link WeatherData} with updated weather data
	 */
	WeatherData updateWeatherData(List<WeatherData> weather);
}
