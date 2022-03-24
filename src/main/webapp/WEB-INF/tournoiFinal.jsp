<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="add.css">
<title>Ajouter un joueur</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<% 	
	String name = (String)session.getAttribute("nomFinale");
	String annee = (String)session.getAttribute("anneeFinale");
	String type = (String)session.getAttribute("typeFinale");	
	
	if(name == null) {
		name="";
	}	
	if(annee == null) {
		annee = "";
	}
	if(type == null) {
		type = "";
	}
	if(type.equals("H")) {
		type="Hommes";
	}
	if(type.equals("F")) {
		type="Femmes";
	}
	String tournoi = name + " "+ annee + " " + type;	
%>

<div class="add-container">
	<form class="form-group" method="post" action="ajouterjoueur">
		<h1 class="h3 mb-3 font-weight-normal" style="color:#FFF;">Match final</h1>		
		<h3 style="text-align:center;"><%=tournoi %></h3>
		<div class="displayPlayer">
			<div class="labelPlayer">
				<h4>Nom du vainqueur :</h4>
			</div>
			<div class="playerName">
				<h4><c:out value="${joueurs[0].nom}" /></h4>
				<h4><c:out value="${joueurs[0].prenom}" /></h4>
			</div>
		</div>
		<div class="displayPlayer">
			<div class="labelPlayer">
				<h4>Nom du finaliste :</h4>
			</div>
			<div class="playerName">
				<h4><c:out value="${joueurs[1].nom}" /></h4>
				<h4><c:out value="${joueurs[1].prenom}" /></h4>
			</div>
		</div>	
		<div id="buttonDiv2">		
			<a class="cancelLink" href="/tennis/listTournoi" role="button">Retour à la liste</a>				
		</div>
	</form>
</div>
	<script src="javascript.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  
</body>
</html>