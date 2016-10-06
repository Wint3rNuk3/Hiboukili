<%-- 
    Document   : leftMenu
    Created on : 4 oct. 2016, 16:19:30
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="left-menu nav nav-pills nav-stacked">
    
    <c:forEach var="rubrique" items="${ rubriques }">
        
    <li>
        <c:url value="rubrique" var="url">
            <c:param name="rubrique" value="${ rubrique.id }" />
        </c:url>
        <a href="${ url }"><c:out value="${ rubrique.libelle }"></c:out></a>
    </li>
        
    </c:forEach>

<!--    <li>
        <a href="#">Les bons plans</a>
    </li>

    <li> 
        <a href="#">Salon du livre</a>
    </li>

    <li> 
        <a href="#">Les bons plans</a>
    </li>-->
</ul>
