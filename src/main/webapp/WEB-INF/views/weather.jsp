<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="header.jsp"%>
<title>Weather App</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Weather Data</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="/city">Config</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container" id="content">
		<div class="btn-group" role="group">
			<a href="/update" type="button" class="btn btn-primary">Fetch
				weather data</a>
		</div>
		<table class="table" id="weather-data">
			<thead>
				<th />
				<th />
				<th />
				<th />
			</thead>
			<tbody>
				<c:forEach items="${weatherData}" var="weather">
					<tr>
						<td><img
							src="http://openweathermap.org/img/w/${weather.icon}.png" /></td>
						<td><span class="city-name"><c:out
									value="${weather.city.name}" /></span><br /> <span class="date"><tags:localDate
									date="${weather.date}" pattern="MMMM dd, YYY" /></span></td>
						<td><span class="label label-default">${weather.temperature}°C</span>
							${weather.weather}<br /> <c:out
								value="${weather.windSpeed} m/s, ${weather.humidity}%, ${weather.pressure} hpa" /></td>
						<td class="delete"><a type="button" class="btn btn-danger"
							href="/delete/<c:out value="${weather.id}"/>">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		$(document).ready(function() {
			$('#weather-data').DataTable({
				"ordering" : false
			});
		});
	</script>
</body>
</html>