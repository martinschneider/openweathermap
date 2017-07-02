package at.mschneider.weatherapp.serializer;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.MappingJsonFactory;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;
import at.mschneider.weatherapp.serializer.WeatherDeserializer;

@Test
public class WeatherDeserializerTest {

	private WeatherDeserializer target = new WeatherDeserializer();

	@Mock
	private DeserializationContext deserializationContext;

	@BeforeClass
	public void setup() {
		initMocks(this);
	}

	@Test(dataProvider = "testData")
	public void testDeserialize(WeatherData expected, String json)
			throws JsonParseException, JsonProcessingException, IOException {
		WeatherData actual = target.deserialize(new MappingJsonFactory().createParser(json), deserializationContext);
		assertEquals(expected.getWeather(), actual.getWeather());
		assertEquals(expected.getTemperature(), actual.getTemperature());
		assertEquals(expected.getPressure(), actual.getPressure());
		assertEquals(expected.getHumidity(), actual.getHumidity());
		assertEquals(expected.getWindSpeed(), actual.getWindSpeed());
		assertEquals(expected.getIcon(), actual.getIcon());
		assertEquals(expected.getCity(), actual.getCity());
	}

	@DataProvider(name = "testData")
	public Object[][] createWeatherData() {
		return new Object[][] { {
				new WeatherData("Rain", new BigDecimal("5.0"), new BigDecimal("1011"), new BigDecimal("93"),
						new BigDecimal("2.6"), "10n", new City("Salzburg")),
				"{\"coord\":{\"lon\":13.04,\"lat\":47.8},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10n\"}],\"base\":\"stations\",\"main\":{\"temp\":5.00,\"pressure\":1011,\"humidity\":93,\"temp_min\":5.00,\"temp_max\":5.00},\"visibility\":10000,\"wind\":{\"speed\":2.6,\"deg\":340},\"clouds\":{\"all\":90},\"dt\":1493238000,\"sys\":{\"type\":1,\"id\":5933,\"message\":0.0032,\"country\":\"AT\",\"sunrise\":1493179028,\"sunset\":1493230484},\"id\":2766824,\"name\":\"Salzburg\",\"cod\":200}" }, };
	}

}
