<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : creationCompte
    Created on : 6 oct. 2016, 09:13:48
    Author     : cdi302
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body> --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Hiboukilit">

    <jsp:attribute name="styles">
    </jsp:attribute>

    <jsp:attribute name="scripts">
    </jsp:attribute>

    
    
    <jsp:body>
        <h1>Données Personnelles !</h1>
        <form action = "UtilisateurController" method ="GET" />

        Nom<br/>
        <input type ="text" name ="nomTI" value ="${recupNomCompte}" required ></br></br>
        Prenom<br/>
        <input type ="text" name ="prenomTI" value ="${recupPrenomCompte}" required ></br></br>
        Date de naissance<br/>
        <input type ="text" name ="dobTI" value ="${recupDobCompte}" required ></br></br>

        Numéro de téléphone<br/>
        <input type ="text" name ="telTI" value ="${recupTelCompte}" ></br></br>

        <%-- composants pour la creation de compte--%>
        <c:if test="${recupNomCompte==null}">

            Adresse mail<br/>
            <input type ="text" name ="mailTI" value ="${recupMailCompte}" required ></br></br>        
            Mot de passe<br/>
            <input type ="password" name ="mdpTI" required ></br></br>
            Confirmation</br> du mot de passe<br/>
            <input type ="password" name ="confMdpTI" required ></br></br>
            <input type ="submit" name = "creerCompteBT" value ="Créez votre compte"></br></br>

        </c:if>

        <%-- composants pour la modification de compte--%>
        <c:if test="${recupNomCompte!=null}">
            <input type ="submit" name = "modifCompteBT" value ="Modifier"></br></br>
        </c:if>
        <font color="red">${erreurSaisie}</font>
    </form>
<%--    </body>
 </html> --%>
    </jsp:body>
</t:template>