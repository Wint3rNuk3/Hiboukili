<%-- 
    Document   : creationCompte
    Created on : 6 oct. 2016, 09:13:48
    Author     : cdi302
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Creation d'un nouveau compte !</h1>
        <form action = "Controller" method ="GET" />

        Nom<br/>
        <input type ="text" name ="nomTI" value ="${recupNomCompte}" required ></br></br>
        Prenom<br/>
        <input type ="text" name ="prenomTI" value ="${recupPrenomCompte}" required ></br></br>
        Date de naissance<br/>
        <input type ="text" name ="dobTI" value ="${recupDobCompte}" required ></br></br>

        Numéro de téléphone<br/>
        <input type ="text" name ="telTI" value ="${recuptelCompte}" ></br></br>


        Adresse mail<br/>
        <input type ="text" name ="mailTI" value ="${recupMailCompte}" required ></br></br>        
        Mot de passe<br/>
        <input type ="password" name ="mdpTI" required ></br></br>
        Confirmation</br> du mot de passe<br/>
        <input type ="password" name ="confMdpTI" required ></br></br>
        <input type ="submit" name = "creerCompteBT" value ="Créez votre compte"></br></br>
        <font color="red">${erreurSaisie}</font>
    </form>
</body>
</html>
