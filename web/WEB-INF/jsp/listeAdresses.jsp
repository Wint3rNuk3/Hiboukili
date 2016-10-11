<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listeAdresses
    Created on : 10 oct. 2016, 11:47:08
    Author     : cdi302
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Adresses</title>
    </head>
    <body>
        <h1>Mes adresses</h1>
        <a href="UtilisateurController?section=ajouterAdresse"> Ajouter une adresse</a></br></br>
    <c:forEach var="i" items="${listeAdresses}">
               idAdresse : ${i.id} - ¤ ${i.statutAdresse} ¤
        ${i.numero} ${i.voie} ${i.cp} ${i.ville} ${i.complement} ${i.pays.libelle}
        <%-- si statutAdresse = 1, afficher adresse de facturation --%>
        <c:if test="${i.statutAdresse==1}" var="test">
        <a href="UtilisateurController?section=modifierAdresse&defaut=${i.id}">Choisir cette adresse comme adresse de Facturation</a> 
        <a href="UtilisateurController?section=modifierAdresse&modif=${i.id}">Modifier cette adresse</a> <font color = "red">Adresse de Facturation</font> </br>            
        </c:if>
        <c:if test="${i.statutAdresse!=1}" var="test">
        <a href="UtilisateurController?section=modifierAdresse&defaut=${i.id}">Choisir cette adresse comme adresse de Facturation</a> 
        <a href="UtilisateurController?section=modifierAdresse&modif=${i.id}">Modifier cette adresse</a></br>            
        </c:if>
    </c:forEach>
    </body>
</html>
