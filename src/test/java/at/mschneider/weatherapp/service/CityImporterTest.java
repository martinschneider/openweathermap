package at.mschneider.weatherapp.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.repository.CityRepository;
import at.mschneider.weatherapp.service.CityImporter;

@Test
public class CityImporterTest {

	@Mock
	private CityRepository cityRepository;

	private CityImporter target;

	@BeforeClass
	public void setup() {
		initMocks(this);
		target = new CityImporter(cityRepository);
	}

	@Test
	public void testImport() throws JsonParseException, IOException {
		target.importCities();
		ArgumentCaptor<City> argumentCaptor = ArgumentCaptor.forClass(City.class);
		verify(cityRepository, times(2)).save(argumentCaptor.capture());
		List<City> importedCities = argumentCaptor.getAllValues();
		City city1 = new City(707860L, "Hurzuf");
		City city2 = new City(519188L, "Novinki");
		assertTrue(importedCities.contains(city1));
		assertTrue(importedCities.contains(city2));
	}
}
