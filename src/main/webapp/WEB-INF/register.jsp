<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html lang="fr">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="signin.css">

<title>Connexion</title>
</head>
<body style="background-color: #000000;">
	<div class="container">
		<form class="form-signin" method="post" action="register">
			<img class="mb-4" src="logo.png" alt="">
			<h1 class="h3 mb-3 font-weight-normal" style="color: #FFF;">Veuillez vous connecter</h1>
			<label for="name" class="sr-only">Votre nom ou pseudo</label> 
			<input type="text" name="txtName" class="form-control"
				placeholder="votre nom ou pseudo" required autofocus> 
			<label for="inputEmail" class="sr-only">Email address</label> 
			<input type="email" id="inputEmail" name="txtLogin" class="form-control"
				placeholder="Addresse mail" required> 
			<label for="inputPassword" class="sr-only">Votre mot de passe</label> 
			<input type="password" id="inputPassword" name="txtPassword"
				class="form-control" placeholder="Mot de passe" required> 
			<label for="inputPassword2" class="sr-only">Confirmer votre mot de passe</label>
			<input type="password" id="inputPassword2" name="txtPassword2"
				class="form-control" placeholder="Confirmer le mot de passe" required>
			<%
			String message = (String) request.getAttribute("message");
			if (message == null) {
				message = "";
			}
			if (message != "") {
			%>
			<p class="error"><%=message%></p>
			<%
			}
			%>
			<div class="checkbox mb-3" style="color: #FFF;"></div>
			<button class="btn btn-lg btn-primary btn-block"
				style="background-color: #ff750b; border-color: #FFF;" type="submit">S'enregistrer</button>
		</form>
		<form class="form-signin" method="post" action="register">
			<button class="btn btn-lg btn-primary btn-block"
				style="background-color: #ff750b; border-color: #FFF;"
				name="redirect" type="submit">Connectez vous</button>
		</form>
	</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</body>
</html>