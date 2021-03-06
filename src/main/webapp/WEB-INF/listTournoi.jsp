
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html lang="fr">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="starter-template.css">
<title>Liste des tournois</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<main role="main" class="container">

		<div class="starter-template">
			<h1>Liste des Tournois</h1>
			<p class="lead">Bienvenue .... Lorem ipsum dolor sit amet,
				consectetur adipiscing elit, sed do eiusmod tempor incididunt ut
				labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
				exercitation ullamco laboris nisi ut aliquip ex ea commodo
				consequat. Duis aute irure dolor in reprehenderit in voluptate velit
				esse cillum dolor.</p>
		</div>

	</main>
	<!-- /.container -->
	<div class="container">

		<div
			style="padding: 1.5rem; margin-right: 0; margin-left: 0; border-width: .2rem;">
			<a class="btn btn-primary" href="/tennis/ajoutertournoi"
				role="button">Ajouter un tournoi</a> <a class="btn btn-primary"
				href="/tennis/listTournoi" role="button">Afficher tous les
				tournois</a>
		</div>



		<table class="table">
			<thead>
				<tr>
					<th scope="col" style="width: 20%%">Année</th>
					<th scope="col" style="width: 25%">Nom</th>
					<th scope="col" style="width: 20%">Type d'épreuve</th>
					<th scope="col" style="width: 40%%"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listeTournoi}" var="tournoi">
					<tr>
						<td>${tournoi.annee}</td>
						<td>${tournoi.nom}</td>
						<td>${tournoi.type}</td>
						<td>
							<button type="button" class="btn btn-outline-success rowTournoi"
								name="afficherFinale" data-id="${tournoi.id}"
								data-name="${tournoi.nom}" data-annee="${tournoi.annee}"
								data-type="${tournoi.type}">Voir la finale</button>
							<button type="button"
								class="btn btn-outline-primary editTournoiBtn"
								name="modfierTournoi" data-id="${tournoi.id}"
								data-name="${tournoi.nom}" data-annee="${tournoi.annee}"
								data-type="${tournoi.type}">Modifier</button>
							<button type="button"
								class="btn btn-outline-warning remTournoiBtn" name="remove"
								data-id="${tournoi.id}" data-name="${tournoi.nom}"
								data-annee="${tournoi.annee}">Supprimer</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>




	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="javascript.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>