<%-- 
    Document   : formulaireNouvelleAdresse
    Created on : 4 oct. 2016, 17:33:54
    Author     : Marÿn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="formNewAddress.jsp" method="post">
            <h1 align="center">NOUVELLE ADRESSE</h1>
            N° : <input type="text" name="numVoie"/><br/>
            <br/>
            Voie : <input type="text" name="nomVoie"/><br/>
            <br/>
            Complement : <input type="text" name="complement"/><br/>
            <br/>
            Code postal : <input type="number" name="cp"/><br/>
            <br/>
            Ville : <input type="text" name="ville"/><br/>
            <br/>
            
            Facturation : <input type="radio" name="ok"/><br/>
            <br/>
            Livraison : <input type="radio" name="ok"/><br/>
            
            
            <input type="submit" value="Annuler" name="annuler" title="retour à la commande"/>
            <input type="submit" value="Supprimer" name="suppr" title="vider les champs"/>
            <input type="submit" value="Valider" name="valide" title='validez la nouvelle adresse et retournez à la commande'/>
            
            
            
            
            
            
            
        </form>
    </body>
</html>
