<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Distance entre deux villes</title>
</head>

<body>
	<%@include file="menu.jsp"%>
	
	<h2>Distance entre deux villes</h2>

	<form method="POST" action="DistanceVilles">
		<p>
			<select id="villeDep" name="villeDep">
				<option value="Def">-- Choisissez une ville de départ --</option>
				<c:forEach items="${ villes }" var="ville">
					<option value="${ ville.codeCommune }">${ ville.codePostal } - ${ ville.nomCommune }</option>
				</c:forEach>
			</select>
		</p>
	
		<p>
			<select id="villeArr" name="villeArr">
				<option value="Def">-- Choisissez une ville d'arrivée --</option>
				<c:forEach items="${ villes }" var="ville">
					<option value="${ ville.codeCommune }">${ ville.codePostal } - ${ ville.nomCommune }</option>
				</c:forEach>
			</select>
		</p>
		
		<input type="hidden" name="distance" value="calculer" />
		<input type="submit" value="Calculer la distance" />
	</form>

	<c:if test="${!empty resultat }">
		<p>
			<c:out value="${ resultat }" />
		</p>
	</c:if>

</body>
</html>