<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Connexion réussie</h1>
        Bienvenue ${recupLogin} !<br/><br/>
        <form action ="UtilisateurController" method = "POST">
            <input type ="submit" name = "deconnecterBT" value = "Déconnexion" /><br/> <br/> 
        </form>
    </body>
</html>
