package at.mschneider.weatherapp.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.repository.CityRepository;

/**
 * Import cities from json to the database (to initially load all city ids) Not
 * used in the current version!
 */
@Component
public class CityImporter {

	private Logger LOG = LoggerFactory.getLogger(CityImporter.class);

	private CityRepository cityRepository;

	private static final String CITY_LIST_FILE = "city.list.json";

	@Autowired
	public CityImporter(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	// @EventListener(ContextRefreshedEvent.class)
	public void importCities() throws JsonParseException, IOException {
		JsonParser parser = new MappingJsonFactory()
				.createParser(this.getClass().getClassLoader().getResourceAsStream(CITY_LIST_FILE));
		if (parser.nextToken() == JsonToken.START_ARRAY) {
			while (parser.nextToken() != JsonToken.END_ARRAY) {
				JsonNode node = parser.readValueAsTree();
				City city = new City(node.get("id").asLong(), node.get("name").asText());
				cityRepository.save(city);
				LOG.info("Imported city {}", city);
			}
		} else {
			throw new RuntimeException("Expected JSON array.");
		}
	}
}