<%-- 
    Document   : index
    Created on : 3 oct. 2016, 16:05:02
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Hiboukilit</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" href="icon.png">

        <style>
            body { 
                padding-top: 70px; 
            }

            img.edition{
                width: 100px;
                height: 80px;
            }

            .left-menu {
                position: fixed;
            }


        </style>

        <!-- import de bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">

        <!--[if lt IE 9]>
            <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
            <script>window.html5 || document.write('<script src="js/vendor/html5shiv.js"><\/script>')</script>
        <![endif]-->
    </head>
    <body>

        <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->


        <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
            <div class="container-fluid">

                <div class="navbar-header">

                    <!-- menu pour la naviguation des petits ecrans -->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="images/icon.png">
                    </a>
                </div>

                <div id="navbar" class="navbar-collapse collapse">
                    
                    <ul class="nav navbar-nav">
                        <li class="active">
                            <a href="#">
                                Link 
                                <span class="sr-only">(current)</span>
                            </a>
                        </li>
                        <li>
                            <a href="#">Link</a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                    
                    <div class="nav navbar-nav navbar-right">
                        <c:url value="shoppingcart" var="url" />
                        <a class="btn btn-default navbar-btn" href="${ url }">
                            <i class="glyphicon glyphicon-shopping-cart"></i>
                        </a>
                    <c:choose>
                        
                        <c:when test="${ utilisateur != null }">
                            <button class="btn btn-default navbar-btn dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
                                <i class="glyphicon glyphicon-user"></i>
                            </button>
                            <ul class="dropdown-menu">
                                <li class="dropdown-header"><span>Vous etes connecté: <c:out value="${ utilisateur.nom }"/></span></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">One more separated link</a></li>
                            </ul>
                        </c:when>
                            
                        <c:otherwise>
                            <c:url value="UtilisateurController" var="url" />
                            <a class="btn btn-default navbar-btn" href="${ url }"> 
                                <i class="glyphicon glyphicon-log-in"></i>
                                Se connecter
                            </a>
                        </c:otherwise>
                        
                    </c:choose>
                        
                    </div>
                    
                </div><!--/.navbar-collapse -->

            </div>
        </nav>

        <%-- Application container --%>
        <section class="container-fluid">

            <div class="row">

                <%-- left-nav --%>
                <div class="col-md-3 col-sm-3 col-xs-3">

                    <ul class="left-menu nav nav-pills nav-stacked">

                        <li>
                            <a href="#">Les bons plans</a>
                        </li>

                        <li> 
                            <a href="#">Salon du livre</a>
                        </li>

                        <li> 
                            <a href="#">Les bons plans</a>
                        </li>
                    </ul>

                </div><%-- Fin left-nav --%>

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

                    <!--<div class="row">-->
                    <%-- On parcourt la liste des editions / ouvrages --%>
                    <ul class="media-list">
                        <c:forEach varStatus="status" var="edition" items="${ editions }" end="${ perPage }">

                            <li class="col-md-12 col-sm-12 col-xs-12 media thumbnail">

                                <div class="media-left pull-left col-md-3 col-sm-3 col-xs-3">
                                    <a href="#">
                                        <img class="edition media-object" src="images/placeholder.png">
                                    </a>
                                </div>

                                <div class="media-body col-md-7 col-sm-7  col-xs-7">
                                    <h4 class="media-heading">
                                        <c:out value="${ edition.ouvrage.titre }"/>
                                    </h4>
                                    
                                    <p>
                                        Résumé : 
                                        <c:out value="${ edition.ouvrage.resume }"/>
                                    </p>
                                    
                                    <p>
                                        Statut : 
                                        <c:out value="${ edition.statut.libelle }"/>
                                    </p>
                                    
                                    <p>
                                        Auteur : 
                                        <c:out value="${ edition.ouvrage.auteur.nom }"/>
                                    </p>
                                    
                                    <p>
                                        Quantité : 
                                        <c:out value="${ edition.stock }"/>
                                    </p>
                                </div>

                                <div class="media-right col-md-2 col-sm-2  col-xs-2">
                                    <div class="pull-right btn-group btn-group-vertical" role="group" aria-label="...">
                                        <a role="button" class="btn btn-success"><i class="glyphicon glyphicon-shopping-cart"> </i></a>
                                        <a role="button" class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"> </i></a>
                                        <a role="button" class="btn btn-primary"><i class="glyphicon glyphicon-heart-empty"> </i></a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                    <!--</div>-->

                    <c:if test="${ nbPage > 1 }">
                    <%-- Pagination --%>
                    <div class="text-center">
                        <nav aria-label="Page navigation">
                            <ul class="pagination">
                                <li class="<c:if test="${ page < 2 }">disabled</c:if>">
                                    <c:url value="" var="url">
                                        <c:param name="page" value="${ 1 }" />
                                        <c:param name="perPage" value="${ perPage }" />
                                    </c:url>
                                    <a href="${ url }" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>

                                <c:forEach varStatus="status" begin="1" end="${ nbPage }">

                                    <c:url value="" var="url">
                                        <c:param name="page" value="${ status.index }" />
                                        <c:param name="perPage" value="${ perPage }" />
                                    </c:url>

                                    <li class="<c:if test="${ status.index == page }"> active </c:if>">
                                        <a href="<c:out value="${ url }"/>"><c:out value="${ status.index }"/></a>
                                    </li>
                                </c:forEach>

                                <li class="<c:if test="${ page >= nbPage}">disabled</c:if>">
                                    <c:url value="" var="url">
                                        <c:param name="page" value="${ nbPage }" />
                                        <c:param name="perPage" value="${ perPage }" />
                                    </c:url>
                                    <a href="${ url }" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div><%-- Fin pagination --%>
                    </c:if>
                </div><%-- Fin colonne centrale --%>
            </div><%-- Fin row --%>

            <footer class="row well">
                <div class="col-md-4">

                </div>

                <div class="col-md-4">

                </div>

                <div class="col-md-4">

                </div>
            </footer>

        </section> <!-- /container -->        

        <!--<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> --> 
        <!--<script>window.jQuery || document.write('<script src="js/vendor/jquery-1.11.2.min.js"><\/script>')</script> --> 

        <script src="js/vendor/jquery-1.11.2.min.js"></script>
        <script src="js/vendor/bootstrap.min.js"></script>

        <script src="js/plugins.js"></script>
        <script src="js/main.js"></script>

        <!-- Google Analytics: change UA-XXXXX-X to be your site's ID.
        <script>
            (function(b,o,i,l,e,r){b.GoogleAnalyticsObject=l;b[l]||(b[l]=
            function(){(b[l].q=b[l].q||[]).push(arguments)});b[l].l=+new Date;
            e=o.createElement(i);r=o.getElementsByTagName(i)[0];
            e.src='//www.google-analytics.com/analytics.js';
            r.parentNode.insertBefore(e,r)}(window,document,'script','ga'));
            ga('create','UA-XXXXX-X','auto');ga('send','pageview');
        </script> 
        -->
    </body>
</html>

