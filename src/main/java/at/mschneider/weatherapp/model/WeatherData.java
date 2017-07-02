package at.mschneider.weatherapp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import at.mschneider.weatherapp.serializer.WeatherDeserializer;

/**
 * Model to represent weather data
 */
@Entity
@JsonDeserialize(using = WeatherDeserializer.class)
public class WeatherData {

	public WeatherData() {

	}

	/**
	 * Constructor used for unit tests
	 */
	public WeatherData(String weather, BigDecimal temperature, BigDecimal pressure, BigDecimal humidity,
			BigDecimal windSpeed, String icon, City city) {
		this.weather = weather;
		this.temperature = temperature;
		this.pressure = pressure;
		this.humidity = humidity;
		this.windSpeed = windSpeed;
		this.icon = icon;
		this.city = city;
	}

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private LocalDate date;

	@Min(-100)
	@Max(60)
	private BigDecimal temperature;

	@Size(max = 20)
	private String weather;

	@Max(1100)
	@Min(900)
	private BigDecimal pressure;

	@Max(100)
	@Min(10)
	private BigDecimal humidity;

	@Max(500)
	@Min(0)
	private BigDecimal windSpeed;

	@Size(max = 50)
	private String icon;

	@ManyToOne
	private City city;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public BigDecimal getTemperature() {
		return temperature;
	}

	public void setTemperature(BigDecimal temperature) {
		this.temperature = temperature;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public BigDecimal getPressure() {
		return pressure;
	}

	public void setPressure(BigDecimal pressure) {
		this.pressure = pressure;
	}

	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	public BigDecimal getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(BigDecimal windSpeed) {
		this.windSpeed = windSpeed;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "WeatherData [id=" + id + ", date=" + date + ", temperature=" + temperature + ", weather=" + weather
				+ ", pressure=" + pressure + ", humidity=" + humidity + ", windSpeed=" + windSpeed + ", icon=" + icon
				+ ", city=" + city + "]";
	}

}
