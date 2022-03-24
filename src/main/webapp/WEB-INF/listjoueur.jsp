  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html lang="fr">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="starter-template.css">
    <title>Liste des joueurs</title>
  </head>
  <body>
<%@ include file="menu.jsp" %>
<main role="main" class="container">
	
  <div class="starter-template">
  <h1>Bienvenue ${user}</h1>
    <h1>Liste des joueurs</h1>
    <p class="lead">Bienvenue .... Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolor.</p>
  </div>

</main><!-- /.container -->
<div class="container">

<div style="    padding: 1.5rem;    margin-right: 0;    margin-left: 0;    border-width: .2rem;">
<a class="btn btn-primary" href="/tennis/ajouterjoueur" role="button">Ajouter un joueur</a>
<a class="btn btn-primary" href="/tennis/listJoueur" role="button">Afficher tous les joueurs</a>
</div>



<table class="table">
  <thead>
    <tr>
      <th scope="col" style="width:25%">Nom</th>
      <th scope="col" style="width:20%">Prenom</th>
      <th scope="col" style="width:20%">Sexe</th>
	  <th scope="col" style="width:20%"></th>
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${liste}" var="joueur">
    <tr>
      <td>${joueur.nom}</td>
      <td>${joueur.prenom}</td>
      <td>${joueur.sexe}</td>
	  <td>
	    <button type="button" class="btn btn-outline-primary editBtn" name="modfier" 
	    data-id="${joueur.id}" data-name="${joueur.nom}" data-first="${joueur.prenom}" data-sex="${joueur.sexe}">Modifier</button>
		<button type="button" class="btn btn-outline-warning remBtn" name="remove" 
		data-id="${joueur.id}" data-name="${joueur.nom}" data-first="${joueur.prenom}">Supprimer</button>
	  </td>
    </tr>
    </c:forEach>
  </tbody>
</table>
</div>




    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="javascript.js"></script>
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
  </body>
</html>