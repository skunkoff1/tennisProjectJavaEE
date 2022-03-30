<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet" href="add.css">
<title>Ajouter un joueur</title>
</head>
<body>
	<%@ include file="menu.jsp"%>
	<%
	String id = (String) session.getAttribute("id");
	String name = (String) session.getAttribute("name");
	String first = (String) session.getAttribute("first");
	String sex = (String) session.getAttribute("sex");
	String sexToDisplay = "";
	String mode = (String) session.getAttribute("mode");
	String title = "";
	String current = "";
	String erreur = (String) session.getAttribute("erreur");	
	if (erreur == null) {
		erreur = "";
	}
	if (sex == null) {
		sex = "";
	}
	if (sex.equals("F")) {
		sexToDisplay = "Femme";
	}
	if (sex.equals("H")) {
		sexToDisplay = "Homme";
	}
	if (mode != null) {
		if (mode.equals("edit")) {
			title = "Modifier un joueur";
			current = first + " " + name + " " + sexToDisplay;
		}
	} else {
		mode = "";
		title = "Ajouter un joueur";
	}
	if (name == null) {
		name = "";
	}
	if (first == null) {
		first = "";
	}
	%>
	<div class="add-container">
		<form class="form-group" method="post" action="ajouterjoueur">
			<h1 class="h3 mb-3 font-weight-normal" style="color: #FFF;"><%=title%></h1>
			<%
			if (current != "") {
			%>
			<h3 style="text-align: center;">
				Joueur à modifier :
				<%=current%></h3>
			<%
			}
			%>
			<div class="input-group mb-3">
				<label for="playerName" class="label">Nom du Joueur</label> 
				<input type="text" name="playerName" class="form-control"
					placeholder="Nom du joueur" value="${name}" required autofocus>
			</div>
			<div class="input-group mb-3">
				<label for="playerFirstName" class="label">Prénom du Joueur</label>
				<input type="text" name="playerFirstName" class="form-control"
					placeholder="Prénom du joueur" value="${first}">
			</div>
			<div class="input-group mb-3">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect01">Sexe du joueur</label>
				</div>
				<select class="custom-select" id="playerSex" name="playerSex">
					<option <%if (sex.equals("H")) {%> selected <%}%> value="H">Homme</option>
					<option <%if (sex.equals("F")) {%> selected <%}%> value="F">Femme</option>
				</select>
			</div>
			<div id="buttonDiv">
				<%
				if (mode.equals("edit")) {
				%>
				<button class="btn btn-lg btn-danger btn-block button"
					style="border-color: #FFF;" type="submit" name="cancel">Annuler</button>
				<button class="btn btn-lg btn-success btn-block button"
					style="border-color: #FFF;" type="submit" name="confirm">Confirmer</button>
				<%
				} else {
				%>
				
				<button class="btn btn-lg btn-success btn-block"
					style="background-color: #ff750b; border-color: #FFF;"
					type="submit" name="add">Soumettre</button>
				<%
				}
				%>
			</div>
			<div class="erreur">
				<p><%=erreur%>
			</div>	
		</form>
		<form class="form-group" method="post" action="ajouterjoueur">
		<button class="btn btn-lg btn-danger btn-block button"
					style="border-color: #FFF;" type="submit" name="cancel">Annuler</button>
					</form>
	</div>
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