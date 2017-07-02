package at.mschneider.weatherapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import at.mschneider.weatherapp.exception.WeatherAppException;
import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;

/**
 * Implementation of {@link WeatherService} using the OpenWeatherMap API
 */
@Component
public class OpenWeatherMapService implements WeatherService {

	private static final String APPID_HEADER = "x-api-key";

	private static final Logger LOG = LoggerFactory.getLogger(OpenWeatherMapService.class);

	@Value("${openweathermap.url}")
	private String baseUrl;

	@Value("${openweathermap.api}")
	private String apiKey;

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	public WeatherData updateWeatherData(WeatherData original) {
		return merge(importWeatherData(original.getCity()), original);
	}

	@Override
	public WeatherData importWeatherData(City city) {
		restTemplate.setMessageConverters(getMessageConverters());
		HttpHeaders headers = new HttpHeaders();
		headers.set(APPID_HEADER, apiKey);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = this.baseUrl + String.format(Locale.ROOT, "weather?q=%s&units=metric", city.getName());
		LOG.info("Retrieving weather data for {} from openweathermap.org API at {}", city.getName(), url);
		return restTemplate.exchange(url, HttpMethod.GET, entity, WeatherData.class).getBody();
	}

	/**
	 * package private for unit test
	 */
	void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private WeatherData merge(WeatherData updated, WeatherData original) {
		City requestedCity = original.getCity();
		City receivedCity = updated.getCity();
		if (!receivedCity.getName().equals(requestedCity.getName())) {
			// TODO: handle this case properly (e.g. log the problem and
			// continue)
			throw new WeatherAppException("The received city " + receivedCity.getName()
					+ " doesn't match the requested city " + requestedCity.getName());
		}
		updated.setId(original.getId());
		updated.setDate(original.getDate());
		updated.setCity(original.getCity());
		return updated;
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		return converters;
	}

	@Override
	public WeatherData importWeatherData(List<City> cities) {
		throw new UnsupportedOperationException("not yet implemented");
	}

	@Override
	public WeatherData updateWeatherData(List<WeatherData> original) {
		throw new UnsupportedOperationException("not yet implemented");
	}

}
