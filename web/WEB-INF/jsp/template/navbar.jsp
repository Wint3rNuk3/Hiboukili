<%-- 
    Document   : navbar
    Created on : 4 oct. 2016, 16:15:19
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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