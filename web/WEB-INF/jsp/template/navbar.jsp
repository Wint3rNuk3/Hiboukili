<%-- 
    Document   : navbar
    Created on : 4 oct. 2016, 16:15:19
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:navbar id="main-nav-bar" brandUrl="/" fluid="true" style="navbar-default navbar-fixed-top">
    <jsp:attribute name="brand">
        Hiboukili
        <!--<img alt="Brand" src="images/icon.png">-->
    </jsp:attribute>
    <jsp:body>
        
        <ul class="nav navbar-nav navbar-left">
            
            <li class="visible-lg visible-md">
                <a href="#" id="menu-toggle"> 
                    <i class="glyphicon glyphicon-chevron-left"></i>
                </a>
            </li>
            
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
        
        <div id="brand" class="visible-lg visible-md">
            <a class="logo" href="/">
                <img src="images/logo_md.png" />
            </a>
        </div>

        <ul class="nav navbar-nav navbar-right">
            <c:url value="shoppingcart" var="url" />
            
            <li>
                <a class="btn navbar-btn" href="shoppingcart" > <!--href="#cartModal" data-toggle="modal" data-target="#cartModal"-->
                    <i class="glyphicon glyphicon-shopping-cart"></i>
                </a>
            </li>
            
            <li>
                <c:choose>
                    <c:when test="${ utilisateur != null }">
                        <button class="btn navbar-btn dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
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
                        <a class="btn navbar-btn" href="${ url }"> 
                            <i class="glyphicon glyphicon-log-in"></i>
                            Se connecter
                        </a>
                    </c:otherwise>

                </c:choose>
            </li>

        </ul>
    </jsp:body>
</t:navbar>
