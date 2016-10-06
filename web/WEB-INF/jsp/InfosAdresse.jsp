<%-- 
    Document   : InfosAdresse
    Created on : 6 oct. 2016, 09:15:04
    Author     : cdi302
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Infos Adresse </h1>

        <form action ="Controller" method="GET">
            Numéro</br>
            <input type ="text" name="numAdresseTI"><br/><br/>

            Rue, Allée, Voie...</br>
            <input type ="text" name="rueTI" required><br/><br/>

            Code postal</br>
            <input type ="text" name="cpTI" required><br/><br/>

            Ville</br>
            <input type ="text" name="villeTI" required><br/><br/>
            
            Pays<br/>
        <Select name ="paysSL"><%--nomme le Combobox ici et non pas ds option !!! --%>
            <c:forEach var="i" items="${liste}"><%--recupere les valeurs de la map(cad les objets ClassePays
 - le nom va changer - avec les proprietes idPays et libelle)--%>
                <OPTION value = "${i.idPays}" >"${i.libelle}"</OPTION>

            </c:forEach>
        
        </select></br></br>

            Infos Complémentaires</br>
            <input type ="text" name="infosCompTI" ><br/><br/>
            
            <input type ="submit" name="validerAdresseBT" value ="Valider l'adresse">

        </form>
    </body>
</html>
