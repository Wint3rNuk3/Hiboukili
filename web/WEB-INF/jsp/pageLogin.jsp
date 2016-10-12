<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Connexion">

    <jsp:attribute name="styles">
    </jsp:attribute>

    <jsp:attribute name="scripts">
    </jsp:attribute>

    <jsp:body>
        <div class="panel panel-default col-md-4 col-md-offset-4">
            <div class="panel-body">
                
                <form class="form-horizontal" action="UtilisateurController" method="POST">

                    <input type="hidden" name="section" value="sectionLogin" />

                    <div class="form-group">
                        <label for="login">Login</label>
                        <input id="login" class="form-control" type="email" name="loginTI" value="${recupLogin}" placeholder="Email"/>
                    </div>

                    <div class="form-group">
                        <label for="pwd">Mot de passe</label>
                        <input id="pwd" type="password" class="form-control" name="mdpTI" value="" placeholder="Mot de passe" />
                    </div>

                    <div class="text-center">
                        <input class="btn btn-success" type="submit" name="validerBT" value="Valider" />
                        <a class="btn btn-default" href="UtilisateurController?section=versNouveauCompte">Créer un nouveau compte</a>
                    </div>
                </form>
            </div>
        </div>
        <font Color = "red">${msgLogin}</font>
    </jsp:body>
</t:template>
