<%-- 
    Document   : navbar
    Created on : 4 oct. 2016, 16:15:19
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
            
            <c:if var="active" test="${ fn:startsWith(pageContext.request.getServletPath(), '/WEB-INF/jsp/rubrique')}"/>
            <li class="dropdown <c:if test="${active}">active</c:if> visible-sm visible-xs">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Rubriques <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="rubrique" items="${ rubriques }">
                        <li>
                            <c:url value="rubrique" var="url">
                                <c:param name="rubrique" value="${ rubrique.id }" />
                            </c:url>
                            <a href="${ url }"><c:out value="${ rubrique.libelle }"></c:out></a>
                        </li>
                    </c:forEach>
                </ul>
            </li>
            
            <c:if var="active" test="${ fn:startsWith(pageContext.request.getServletPath(), '/WEB-INF/jsp/promotion')}"/>
            <li class="<c:if test="${active}">active</c:if>">
                <c:url value="/promotions" var="url"/>
                <a href="${ url }">Promotions</a>
            </li>
        </ul>
        
        <div id="brand" class="visible-lg visible-md">
            <c:url value="/" var="url"/>
            <a class="logo" href="${url}">
                <img src="images/logo_md.png" />
            </a>
        </div>
        
        <c:url value="/recherche" var="url"/>
        <form class="navbar-form navbar-right" action="${url}">

            <div class="form-group">
                <div class="input-group">
                    <input type="text" class="form-control" id="recherche" name="q" value="${q}" placeholder="Votre recherche">
                    <div class="input-group-btn">
                        <button type="submit" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"> </i>
                        </button>
                    </div>
                </div>
            </div>
        </form>

        
<!--        <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>-->

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
                        <a class="btn navbar-btn dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 
                            <i class="glyphicon glyphicon-user"></i>
                        </a>
                        <ul class="dropdown-menu">
                            <li class="dropdown-header"><span>Vous etes connecté: <c:out value="${ utilisateur.nom }"/></span></li>
                            <li><a href="UtilisateurController?section=modifierInfosPerso">Modifier les infos personnelles</a></li>
                            <li><a href="UtilisateurController?section=gererAdresses">Gérer mes adresses</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="UtilisateurController?section=deconnexion">Déconnexion</a> </li>
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
