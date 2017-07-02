package at.mschneider.weatherapp.serializer;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import at.mschneider.weatherapp.model.City;
import at.mschneider.weatherapp.model.WeatherData;

public class WeatherDeserializer extends JsonDeserializer<WeatherData> {
	@Override
	public WeatherData deserialize(JsonParser parser, DeserializationContext deserializationContext)
			throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);

		WeatherData weatherData = new WeatherData();
		JsonNode main = node.get("main");
		JsonNode wind = node.get("wind");
		JsonNode weather = node.get("weather");

		weatherData.setHumidity(new BigDecimal(main.get("humidity").asText()));
		weatherData.setPressure(new BigDecimal(main.get("pressure").asText()));
		weatherData.setTemperature(new BigDecimal(main.get("temp").asText()));
		if (weather.has(0)) {
			weatherData.setWeather(weather.get(0).get("main").asText());
			weatherData.setIcon(weather.get(0).get("icon").asText());
		}
		weatherData.setWindSpeed(new BigDecimal(wind.get("speed").asText()));
		weatherData.setCity(new City(node.get("name").asText()));
		return weatherData;
	}
}
