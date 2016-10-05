<%-- 
    Document   : template
    Created on : 5 oct. 2016, 14:00:30
    Author     : cdi305
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="title" required="true" description="Title"%>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="scripts" fragment="true" %>

<c:set var="baseDIR" value="/WEB-INF/jsp/"/>

<!doctype html>
<%-- Cette partie permet de traiter differement les versions de IE --%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><c:out value="${ title }"/></title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <%-- Importation des styles --%>
        <c:import url="${ baseDIR }/template/styles.jsp" />

        <%-- Ajout des styles specifiques a la page. --%>
        <jsp:invoke fragment="styles"/>

        <!--[if lt IE 9]>
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
            <script>window.html5 || document.write('<script src="js/vendor/html5shiv.js"><\/script>')</script>
        <![endif]-->
    </head>
    <body>

        <%-- 
            Importation de la jsp contenant le code servant à alerter
            l'utilisateur que son navigateur est obsolète.
        --%>
        <c:import url="${ baseDIR }template/browserAlert.jsp" />
        <%--<c:import url="template/browserAlert.jsp" />--%>

        <%--
            Importation de la jsp contenant la navbar. (menu du haut)
        --%>
        <c:import url="${ baseDIR }template/navbar.jsp"/>

        <%-- 
            Section principale de l'application, 
            elle est découpée en deux colonnes :
                - le menu de gauche ().
                - l'affichage au centre.
        --%>
        <section class="container-fluid">

            <div class="row">
                
                <%-- left-nav --%>
                <div class="col-md-3 col-sm-3 col-xs-3">
                    <%-- 
                        Importation de la jsp contenant le menu de gauche 
                    --%>
                    <c:import url="${ baseDIR }template/leftMenu.jsp"/>

                    <%-- Fin left-nav --%>
                </div>

                <%-- Colonne centrale --%>
                <div class="col-md-9 col-sm-9 col-xs-9">

                <jsp:doBody />
                
                <%-- Fin colonne centrale --%>
                </div>

            <%-- Fin row --%>
            </div>

            <%-- Importation de la jsp contenant le footer --%>
            <c:import url="${ baseDIR }template/footer.jsp"/>

            <%-- fin section container --%>
        </section>        

        <%-- Importation de la jsp contenant les scripts javascript --%>
        <c:import url="${ baseDIR }template/scripts.jsp"/>

        <%-- Ajout des scripts specifiques a la page. --%>
        <jsp:invoke fragment="scripts"/>

    </body>
</html>