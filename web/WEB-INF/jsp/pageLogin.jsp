<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action ="UtilisateurController" method = "POST">
            <input type="hidden" name="section" value="sectionLogin" />
            Login<br/> 
            <input type ="text" name = "loginTI" value = "${recupLogin}" /><br/> <br/> 
            Mot de passe<br/> 
            <input type ="password" name = "mdpTI" value = "" /><br/> <br/> 
            <input type ="submit" name = "validerBT" value = "Valider" /><br/> <br/> 
        </form>
            <a href ="UtilisateurController?section=versNouveauCompte">Créer un nouveau compte</a></br></br>
        <font Color = "red">${msgLogin}</font>
    </body>
</html>
