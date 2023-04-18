<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste des villes</title>
<style>
table, th, td {
	border: 2px solid white;
	border-collapse: collapse;
}

th {
	background-color: #62C9FF;
}

td {
	background-color: #C1EAFF;
}
</style>

</head>
<body>
	<%@include file="menu.jsp"%>

	<h2>Liste des villes</h2>
	<a href="<%=request.getContextPath()%>/FormVille?ajouter">Ajouter
		une nouvelle ville</a>

	<table style="width: 100%">
		<tr>
			<th>Code INSEE</th>
			<th>Nom</th>
			<th>Code Postal</th>
			<th>Libellé d'acheminement</th>
			<th>Ligne</th>
			<th>Latitude</th>
			<th>Longitude</th>
			<th>Météo</th>
			<th>Action</th>
		</tr>
		<c:forEach var="ville" items="${villes}">
			<tr class="clickable"
				onclick="window.location='<%=request.getContextPath()%>/FormVille?editer&<c:out value="${ville.codeCommune}"/>'"
				style="cursor: hand">
				<td><c:out value="${ville.codeCommune}" /></td>
				<td><c:out value="${ville.nomCommune}" /></td>
				<td><c:out value="${ville.codePostal}" /></td>
				<td><c:out value="${ville.libelleAcheminement}" /></td>
				<td><c:out value="${ville.ligne}" /></td>
				<td><c:out value="${ville.latitude}" /></td>
				<td><c:out value="${ville.longitude}" /></td>
				<td><c:out value="${ville.temperature}" /></td>
				<td style="text-align: center;"><input type="hidden"
					name="codeCommune" value="${ville.codeCommune}"> <a
					href="<%=request.getContextPath()%>/FormVille?editer&<c:out value="${ville.codeCommune}"/>">Modifier</a>
					&nbsp;&nbsp;&nbsp;&nbsp; <a
					href="<%=request.getContextPath()%>/FormVille?supprimer&<c:out value="${ville.codeCommune}"/>">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<nav style="text-align: center;">
		<ul style="display: inline-block; padding: 0; margin: 0;">
			<c:forEach var="i" begin="1" end="${size - 1}">
				<li style="display: inline-block; margin-right: 2px;"><a
					href="?page=<c:out value="${i}"/>"><c:out value="${i}" /></a></li>
			</c:forEach>
		</ul>
	</nav>
</body>
</html>