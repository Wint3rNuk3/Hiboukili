<%-- 
    Document   : navbar
    Created on : 5 oct. 2016, 16:05:51
    Author     : cdi305
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="id" required="true" description="The HTML id attribute for navbar"%>
<%@attribute name="style" description="default or inverse"%>
<%@attribute name="fluid" description="true or false"%>
<%@attribute name="brand" fragment="true"%>
<%@attribute name="brandUrl" description="The href value for brand"%>

<c:set var="containerClass" value="${(fluid eq false) ? 'container' : 'container-fluid'}"/>
<c:set var="brandUrl" value="${(empty brandUrl) ? '#' : brandUrl}"/>

<nav class="navbar <c:out value="${ style }" default="navbar-default"/>" role="navigation">
    <div class="<c:out value="${ containerClass }"/>">

        <div class="navbar-header">

            <!-- menu pour la naviguation des petits ecrans -->
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#${ id }" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <c:url value="${ brandUrl }" var="url"/>
            
            <a class="navbar-brand visible-xs" href="<c:out value="${ url }"/>">
                <jsp:invoke fragment="brand" />
            </a>
            
        </div>

        <div id="<c:out value="${ id }"/>" class="navbar-collapse collapse">
            
            <jsp:doBody/>

        </div><!--/.navbar-collapse -->

    </div>
</nav>