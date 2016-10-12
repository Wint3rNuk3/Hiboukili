

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Hiboukilit">
    
    <jsp:attribute name="styles">
        
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
    </jsp:attribute>
        
         <jsp:body>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="order" method="Post">
            <h1 align="center"> COMMANDE ACCEPTEE</h1>
            <p>
            Voici les informations relatives à votre commande!<br/>
            <br/>
            Numero de commande : 15236DFFFF<br/>
            <%--recuperation du numero de commande via methode SQL bean commande--%>
            <br/>
            Vous pouvez la consulter dans l'historique de vos commandes. <br/>
            <br/>
            Dans la rubrique <a href="moncompte.jsp">Mon Compte</a>, onglet <a href="historiqueCommande.jsp">Historique de commande.</a><br/>
            <%--fake lien, voir avec eric--%>
            <br/>
            </p>
            
            <input type="submit" name="retour" value="Retour Acceuil"/><br/>
            <br/>
            <br/>
            <input type="submit" name="deconnexion" value="Deconnexion"/><br/>
            
            
            
            
            
        </form>
    </body>
</html>
</jsp:body>
</t:template>
