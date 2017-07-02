<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
				<a class="navbar-brand" href="/">Weather Data</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Configuration</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</nav>

	<div class="container" id="content">
		<h3>Add a city</h3>
		<form method="POST" action='/city/add' name="addCityForm">
			<label>Name</label> <input type="text" name="name"
				value="<c:out value="${city.name}" />" /> <input type="submit"
				value="Add" class="btn" />
		</form>
		<table class="table" id="cities">
			<thead>
				<th />
				<th />
			</thead>
			<tbody>
				<c:forEach items="${cities}" var="city">
					<tr>
						<td class="city-name"><c:out value="${city.name}" /></td>
						<td class="delete"><a type="button" class="btn btn-danger"
							href="/city/delete/<c:out value="${city.id}"/>">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<script>
		$(document).ready(function() {
			$('#cities').DataTable({
				"columnDefs" : [ {
					"orderable" : false,
					"targets" : 1
				} ]
			});
		});
	</script>
</body>
</html>
