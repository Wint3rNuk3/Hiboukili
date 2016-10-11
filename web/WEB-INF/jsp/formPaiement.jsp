<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="OrderController" method="Post">
        <%-- le paiement est une sorte de fake. on a pas à gerer ca donc dans ce cas là 
            le paiement sera accepté sir les chiffres de CB correspondent bien à la regex mise en placeù--%>
        <h1 align="center">COORDONNEES DE PAIEMENT</h1>
        <br/>
        <br/>
        Numero CB : <input type="number" name="cb"/><br/>
        <br/>
        Date d'expiration : <input type="date" name="date"/><br/>
        <br/>
        Code de securité: <input type="number" name="crypto" title="Correspond aux 3 chiffres visibles au dos de votre carte "/><br/>
        <br/>
        Nom du porteur :<input type="text" name="nom"/><br/>
        <br/>
        <br/>
        <br/>
        <p>
            choisissez le type de votre carte :
            <br/>
            <br/>
            <img src="images/visa.png" alt="picto carte visa"/>
            <input type="radio" name="carte"/>
            <br/>
            <br/>
            <img src="images/amEx.png" alt="picto carte american express"/>
            <input type="radio" name="carte"/>
            <br/>
            <br/>
            <img src="images/masterCard.png" alt="picto carte masterCard"/>
            <input type="radio" name="carte"/>
            <br/>
            <br/>
            <img src="images/carteBleu.png" alt="picto carte bleu"/>
            <input type="radio" name="carte"/>
            <br/>
            <br/>
              
            
        </p>
        
        <br/>
        <br/>
        <input type="submit" name="annuler" value="Annuler"/><br/>
        <br/>
        <input type="submit" name="valider" value="Payer"/><br/>
        
            
            
            
            
            
        </form>
    </body>
</html>
 