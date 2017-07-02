package at.mschneider.weatherapp.controller;

import java.net.UnknownHostException;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

import at.mschneider.weatherapp.exception.WeatherAppException;

/**
 * Very basic error handling
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String DATABASE_CONNECTION_ERROR = "Could not connect to database.";
	private static final String CONNECTION_ERROR = "Could not connect to host: %s";

	@ExceptionHandler({ JDBCConnectionException.class, CommunicationsException.class })
	public ResponseEntity<Error> handleDBConnectionError(JDBCConnectionException ex) {
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(), DATABASE_CONNECTION_ERROR);
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UnknownHostException.class)
	public ResponseEntity<Error> handleHostException(UnknownHostException ex) {
		Error error = new Error(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				String.format(CONNECTION_ERROR, ex.getMessage()));
		return new ResponseEntity<Error>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(WeatherAppException.class)
	public ResponseEntity<Error> handleAppException(WeatherAppException ex) {
		Error error = new Error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
		return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	}

	class Error {
		private int code;
		private String message;

		public Error() {
		}

		public Error(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}
}
