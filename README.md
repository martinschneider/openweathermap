# Preconditions

1. Get an API key for OpenWeatherMap and set it in application.properties:

```
openweathermap.api = xxx
```

2. Set the database properties in application.properties:
```
jdbc.url = jdbc:mysql://localhost:3306/weather
jdbc.username = 
jdbc.password = 
```

# Usage

1. Build the project and deploy it in an in-memory Tomcat:
```sh
$ mvn clean install tomcat7:run
```

2. In your browser navigate to
```
localhost:9090
```

Alternatively, you can use mvn clean install and deploy the WAR-file in any standalone Tomcat.

# First steps
* Click on "Update weather data" to fetch the current weather for all cities configured in the database. Note, that 10 cities are initialized on setting up the context. 

# Limitations (and possible improvements)
* Weather data is limited to one entry per city and date. If data is fetched again any existing data for the same day is overwritten.
* If a city is removed all weather data for the city is removed as well and no more weather data will be queried for it. This could be configurable, e.g. allow disabling a city for future updates while still keeping historic data.
* All weather data is fetched by city name (vs. city id, which the API also supports). For this reason bulk requests are currently not used although some parts of it have been implemented (see the javadoc for comments on what changes would be necessary).
* Filtering (searching) and sorting is currently done on the frontend. This is not suitable for larger amounts of data.
* hibernate.hbm2ddl.auto = update is used which is apparently not the right choice in a productive setup. Use Liquibase (or similar) for DB versioning
* Detailed error handling and handling of invalid/faulty data from the API is not implemented! What is handled are invalid city names.
* Unit tests need to be extended! 
