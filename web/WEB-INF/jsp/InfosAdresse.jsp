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

        <form action ="UtilisateurController" method="GET">
            Numéro</br>
            <input type ="text" name="numAdresseTI" value="${recupNumAdresse}"><br/><br/>

            Rue, Allée, Voie...</br>
            <input type ="text" name="voieTI" value="${recupVoieAdresse}" required><br/><br/>

            Code postal</br>
            <input type ="text" name="cpTI" value="${recupCpAdresse}" required><br/><br/>

            Ville</br>
            <input type ="text" name="villeTI" value="${recupVilleAdresse}" required><br/><br/>

            Pays<br/>
            <Select name ="paysSL" ><%--nomme le Combobox ici et non pas ds option !!! --%>
                <c:forEach var="i" items="${liste}"><%--recupere les valeurs de la map(cad les objets ClassePays
     - le nom va changer - avec les proprietes idPays et libelle)--%>

                    
                    <%-- si i.libelle (l'element de la liste) = le pays de l'utilisateur 
(condition valable uniquement pour la modification d'adresse, pas pour la creation), alors selectionner
ce pays a l'affichage --%>
                    <c:if test="${i.libelle==recupPaysAdresse}" var="test">
                        <OPTION value = "${i.id}" selected>${i.libelle}</OPTION>
                    </c:if>
<%-- sinon ajouter le pays a la liste --%>
                    <c:if test="${i.libelle!=recupPaysAdresse}" var="test">
                        <OPTION value = "${i.id}" >${i.libelle}</OPTION>
                    </c:if>
                        
                </c:forEach>
            </select></br></br>

            Infos Complémentaires</br>
            <input type ="text" name="infosCompTI" value="${recupInfosCompAdresse}" ><br/><br/>

            <c:if test="${recupCpAdresse==null}" var="test">
                <%-- si le champ code postal est vide, afficher le bouton de validation --%>
                <br/><input type ="submit" name="validerAdresseBT" value ="Valider l'adresse" ><br/><br/>     
            </c:if>

            <c:if test="${recupCpAdresse!=null}" var="test">
                <%-- si le champ code postal est rempli, afficher le bouton de modification --%>                
                <input type ="submit" name="modifierAdresseBT" value ="Modifier l'adresse">            
            </c:if>
        </form>
    </body>
</html>
