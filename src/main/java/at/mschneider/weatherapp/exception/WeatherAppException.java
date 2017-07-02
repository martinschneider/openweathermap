package at.mschneider.weatherapp.exception;

// TODO: this should be a checked exception and properly handled
public class WeatherAppException extends RuntimeException {

	public WeatherAppException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 1L;

}
