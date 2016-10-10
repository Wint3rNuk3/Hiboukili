<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--<link rel="stylesheet" href="newcss.css">--%>
        <form action="OrderController.jsp" method="post">

            <h1 align="center">COMMANDE</h1>
            <h3>INFORMATIONS GENERALES</h3>
            <fieldset>
                <table>
                    <tr>
                        <th>date</th>
                        <th>total d'articles</th>
                        <th>Prix total TTC</th>
                        <th>Statut Commande</th>    
                    </tr>
                    <tr>
                        <%--rempalcer les valeurs en durs par les valeurs recuperer via le panier/session grace EL--%>
                        <td>01/10/2016</td>
                        <td>5</td>
                        <td>50 euros</td>
                        <td>En cours de validation</td>
                    </tr>  
                </table>
            </fieldset>
            <br/>      
            <br/>
            <h3>CHOIX ADRESSE</h3>
            <fieldSet>
                <legend>Adresse de facturation</legend><br/>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${empty adresse}">
                        Adresse vide !
                        
                    </c:when>
                        <c:otherwise>
                            <select name="adresse">
                            <c:forEach items="${adresse}" var="a">
                            <option value="adresse1">${a.numero},${a.voie},${a.cp},${a.ville},${a.complement}</option>
                            <option value="adresse2">52 avenues des Galeres 45869 CodeVille, troisieme synapse à gauche</option> 
                            </select> 
                            </c:forEach>
                        </c:otherwise>
                </c:choose>
            </fieldSet>
            <br/>
            <div align="right">
                <input type="submit" value="Ajouter" name="ajout" title="Ajouter une nouvelle adresse"/>
            </div>
            <br/>
            <fieldset>
                <legend>Adresse de livraison</legend><br/>
                <br/>
                <br/>
                <c:choose>
                    <c:when test="${empty adresse}">
                        Adresse vide !
                        
                    </c:when>
                        <c:otherwise>
                            <select name="adresse">
                            <c:forEach items="${adresse}" var="a">
                            <option value="adresse1">${a.numero},${a.voie},${a.cp},${a.ville},${a.complement}</option>
                            <option value="adresse2">52 avenues des Galeres 45869 CodeVille, troisieme synapse à gauche</option> 
                            </select> 
                            </c:forEach>
                        </c:otherwise>
                </c:choose>
            </fieldset>
            <br/>
            <div align="right">
                <input type="submit" value="Ajouter" name="ajout" title="Ajouter une nouvelle adresse"/>
            </div>
            <br/>
            <br/>
            <input type="submit" value ="Valider" name="finaliser" title="finalisez votre commande"/>
            <br/>
            <br/>
            <input type="submit" value="Retour" name="retour" title="retournez au récapitulatif de votre commande"/>
            <br/>
            <br/>
    </form>
</body>
</html>
