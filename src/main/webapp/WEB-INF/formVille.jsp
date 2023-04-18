<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des villes</title>
</head>
<body>
	<%@include file="menu.jsp"%>

	<c:if test="${ville != null}">
		<form action="<%=request.getContextPath()%>/FormVille" method="POST">
			<fieldset>
				<legend> Éditer [${ ville.codeCommune }] ${ ville.nomCommune }</legend>
				<label for="codeCommune">Code commune INSEE : <c:out
						value="${ ville.codeCommune }" /></label> <input type="hidden"
					name="codeCommune" value="<c:out value="${ ville.codeCommune }"/>" />
	</c:if>
	<c:if test="${ville == null}">
		<form action="<%=request.getContextPath()%>/FormVille" method="POST">
			<fieldset>
				<legend> Ajouter une nouvelle ville </legend>
				<p>
					<label for="codeCommune">Code commune INSEE : </label> <input
						type="tel" pattern="[0-9]{5}" id="codeCommune" name="codeCommune"
						title="Code commune INSEE (nombre à 5 chiffres)"
						value="<c:out value="${ ville.codeCommune }"/>" required />
				</p>
	</c:if>

	<p>
		<label for="nomCommune">Nom de la commune : </label> <input
			type="text" pattern="[A-Z ]{2,}" id="nomCommune" name="nomCommune"
			title="Nom de la commune (caractères en majuscule, sans symboles)"
			value="<c:out value="${ ville.nomCommune }"/>" required />
	</p>
	<p>
		<label for="codePostal">Code postal : </label> <input type="tel"
			pattern="[0-9]{5}" id="codePostal" name="codePostal"
			title="Code postal (nombre à 5 chiffres)"
			value="<c:out value="${ ville.codePostal }"/>" required />
	</p>
	<p>
		<label for="libelle">Libellé d'acheminement : </label> <input
			type="text" pattern="[A-Z ]{2,}" id="libelle" name="libelle"
			title="Libellé d'acheminement (caractères en majuscule, sans symboles)"
			value="<c:out value="${ ville.libelleAcheminement }"/>" />
	</p>
	<p>
		<label for="ligne">Ligne : </label> <input type="text"
			pattern="[A-Z ]{2,}" id="ligne" name="ligne"
			title="Ligne (caractères en majuscule, sans symboles)"
			value="<c:out value="${ ville.ligne }"/>" />
	</p>
	<p>
		<label for="latitude">Latitude : </label> <input type="number" min=-90
			max=90 step=0.000000000001 id="latitude" name="latitude"
			title="Latitude (décimal entre -90 et 90)"
			value="<c:out value="${ ville.latitude }"/>" required />
	</p>
	<p>
		<label for="longitude">Longitude : </label> <input type="number"
			min=-90 max=90 step=0.000000000001 id="longitude" name="longitude"
			title="Longitude (décimal entre -90 et 90)"
			value="<c:out value="${ ville.longitude }"/>" required />
	</p>
	</fieldset>
	<c:if test="${ville == null}">
		<button type="submit" name="form" value="ajouter">Ajouter</button>
	</c:if>
	<c:if test="${ville != null}">
		<button type="submit" name="form" value="enregistrer">Enregistrer</button>
	</c:if>
	</form>

	<c:if test="${!empty resultat }">
		<p>
			<c:out value="${ resultat }" />
		</p>
	</c:if>
</body>
</html>