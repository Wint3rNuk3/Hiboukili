<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Connexion réussie</h1>
        Bienvenue ${prenomUtilisateur} !<br/><br/>
        
        <a href="UtilisateurController?section=modifierInfosPerso">Modifier les infos personnelles</a>
        <%--<a href="UtilisateurController?section=modifierAdresse">Modifier l'adresse</a>
        <a href="UtilisateurController?section=ajouterAdresse">Ajouter une adresse</a>--%>
        <a href="UtilisateurController?section=gererAdresses">Gérer mes adresses</a>          
        
        <a href="UtilisateurController?section=deconnexion">Déconnexion</a>  
         
        <form action ="UtilisateurController" method = "POST">
            <input type ="submit" name = "deconnecterBT" value = "Déconnexion" /><br/> <br/> 
        </form>
    </body>
</html>
