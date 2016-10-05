<%-- 
    Document   : index
    Created on : 3 oct. 2016, 16:05:02
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>

<%-- Cette partie permet de traiter differement les versions de IE --%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    
    <%-- 
        Importation de la jsp contenant la balise html head 
    --%>
    <c:import url="template/head.jsp"/>
        
    <body>

        <%-- 
            Importation de la jsp contenant le code servant à alerter
            l'utilisateur que son navigateur est obsolète.
        --%>
        <c:import url="template/browserAlert.jsp"/>
        
        <%--
            Importation de la jsp contenant la navbar. (menu du haut)
        --%>
        <c:import url="template/navbar.jsp"/>

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
                    <c:import url="template/leftMenu.jsp"/>
                    
                <%-- Fin left-nav --%>
                </div>

                <%-- Colonne centrale --%>
                <div class="col-md-9 col-sm-9 col-xs-9">

                    <div class="row text-center">

                        <!-- formulaire de recherche -->
                        <form class="form-inline">

                            <div class="form-group">
                                <div class="input-group">
                                    <input type="text" class="form-control" id="recherche" placeholder="Votre recherche">
                                    <div class="input-group-btn">
                                        <button type="submit" class="btn btn-default" >
                                            <i class="glyphicon glyphicon-search"> </i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <hr>

                    <c:import url="listEdition.jsp"/>
                    
                <%-- Fin colonne centrale --%>
                </div>
            <%-- Fin row --%>
            </div>

            <%-- Importation de la jsp contenant le footer --%>
            <c:import url="template/footer.jsp"/>

        <%-- fin section container --%>
        </section>        

        <%-- Importation de la jsp contenant les scripts javascript --%>
        <c:import url="template/scripts.jsp"/>
        
    </body>
</html>

