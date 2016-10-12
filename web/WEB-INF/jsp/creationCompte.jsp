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

<t:template title="Données personnelles">
    
    <jsp:body>
        <div class="col-md-6 col-md-offset-3">
            
            <form action="UtilisateurController" method="GET" class="form-horizontal">

                <div class="form-group">
                    <label for="name">Nom</label>
                    <input id="name" class="form-control" type="text" name="nomTI" value="${recupNomCompte}" required />
                </div>

                <div class="form-group">
                    <label for="firstname">Prenom</label>
                    <input id="firstname" class="form-control" type="text" name="prenomTI" value="${recupPrenomCompte}" required />
                </div>

                <div class="form-group">
                    <label for="birth">Date de naissance</label>
                    <input id="birth" class="form-control" type="date" name="dobTI" value="${recupDobCompte}" required />
                </div>

                <div class="form-group">
                    <label for="phone">Numéro de téléphone</label>
                    <input id="phone" class="form-control" type="tel" name ="telTI" value ="${recupTelCompte}" />
                </div>

                <%-- composants pour la creation de compte--%>
                <c:if test="${recupNomCompte==null}">
                    
                    <div class="form-group">
                        <label for="mail">Adresse mail</label>
                        <input id="mail" class="form-control" type="email" name ="mailTI" value ="${recupMailCompte}" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="pswd">Mot de passe</label>
                        <input id="pswd" class="form-control" type ="password" name ="mdpTI" required />
                    </div>
                    
                    <div class="form-group">
                        <label for="pswd-confirm">Confirmation du mot de passe</label>
                        <input id="pswd-confirm" class="form-control" type ="password" name ="confMdpTI" required />
                    </div>
                    
                    
                    <input class="btn btn-success" type="submit" name="creerCompteBT" value="Créez votre compte"></br></br>

                </c:if>

                <%-- composants pour la modification de compte--%>
                <c:if test="${recupNomCompte!=null}">
                    <input class="btn btn-info" type="submit" name="modifCompteBT" value="Modifier">
                </c:if>
                <font color="red">${erreurSaisie}</font>
            </form>
        </div>
    </jsp:body>
</t:template>