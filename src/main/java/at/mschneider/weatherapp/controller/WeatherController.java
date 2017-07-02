package at.mschneider.weatherapp.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;
import at.mschneider.weatherapp.repository.CityRepository;
import at.mschneider.weatherapp.repository.WeatherRepository;
import at.mschneider.weatherapp.service.WeatherService;

/**
 * Controller to manage (update, list and delete) weather data
 */
@Controller
@RequestMapping("/")
public class WeatherController {

	private WeatherRepository weatherRepository;
	private CityRepository cityRepository;
	private WeatherService weatherService;

	@Autowired
	public WeatherController(WeatherRepository weatherRepository, CityRepository cityRepository,
			WeatherService service) {
		this.weatherRepository = weatherRepository;
		this.cityRepository = cityRepository;
		this.weatherService = service;
	}

	/**
	 * Lists all available weather data
	 */
	@RequestMapping(value = { "", "/list" }, method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("weatherData", weatherRepository.findAll());
		return "weather";
	}

	@RequestMapping(value = { "/update" }, method = RequestMethod.GET)
	public String update() {
		/**
		 * TODO: Fetch weather data in bulks
		 * 
		 * We query using city names. The OWM API allows requesting data in
		 * bulks of 20, but only when using city ids. Implement this as a future
		 * improvement to minimize the number of requests (especially with larger
		 * numbers of cities in our DB).
		 * 
		 * <ul>
		 * <li>use city ids instead of city names for sending requests to OWM</li>
		 * <li>either regularly import the city ids from
		 * http://bulk.openweathermap.org/sample/city.list.min.json.gz or parse
		 * each city's id from the response the first time weather data for that
		 * city is requested from the API</li>
		 * <li>Retrieve cities from our DB in batches of 20</li>
		 * <li>Implement a new {@link JsonDeserializer} to handle bulk
		 * results</li>
		 * </ul>
		 */
		for (City city : cityRepository.findAll()) {
            // retrieve existing weather data for city and date			
			WeatherData weather = weatherRepository.findByCityAndDate(city, LocalDate.now());
			if (weather == null) {
				weather = new WeatherData();
				weather.setCity(city);
				weather.setDate(LocalDate.now());
			}
			weatherRepository.saveAndFlush(weatherService.updateWeatherData(weather));
		}
		return "redirect:/list";
	}

	/**
	 * @param weatherDataId id of the {@link WeatherData} to delete
	 * @return redirects to the list view
	 */
	@RequestMapping(value = "/delete/{weatherDataId}", method = RequestMethod.GET)
	public String delete(@PathVariable Long weatherDataId) {
		weatherRepository.delete(weatherDataId);
		return "redirect:/list";
	}
}
