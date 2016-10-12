<%@page import="model.classes.Adresse"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : listeAdresses
    Created on : 10 oct. 2016, 11:47:08
    Author     : cdi302
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%-- <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Adresses</title>
    </head> 
    <body> --%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Hiboukilit">

    <jsp:attribute name="styles">
    </jsp:attribute>

    <jsp:attribute name="scripts">
    </jsp:attribute>
        
    <jsp:body>



        <h1>Mes adresses</h1>
        <a href="UtilisateurController?section=ajouterAdresse"> Ajouter une adresse</a></br></br>

        <%--ArrayList<Adresse> myal = (ArrayList<Adresse>) session.getAttribute("listeAdresses");
            System.out.println("myal size : " + myal.size() + " WWWW -----------------------------");--%>

        <c:forEach var="i" items="${listeAdresses}">
            idAdresse : ${i.id} - ¤ ${i.statutAdresse} ¤
            ${i.numero} ${i.voie} ${i.cp} ${i.ville} ${i.complement} ${i.pays.libelle}
            <%-- si statutAdresse = 1, afficher adresse de facturation --%>
            <a href="UtilisateurController?section=modifierAdresse&defaut=${i.id}">Choisir cette adresse comme adresse de Facturation</a> 
            <a href="UtilisateurController?section=modifierAdresse&modif=${i.id}">Modifier cette adresse</a> 
            <c:if test="${i.statutAdresse==1}" var="test">
                <font color = "red">Adresse de Facturation</font> 
            </c:if>
            </br>            
        </c:forEach>
<%--    </body>
 </html> --%>
   </jsp:body>
</t:template>