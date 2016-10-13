<%-- 
    Document   : listEdition
    Created on : 4 oct. 2016, 16:20:56
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:list 
    var="item"
    list="${ editions }" 
    page="${ page }" 
    nbPage="${ nbPage }" 
    perPage="${ perPage }" 
    paginationUrl="" 
    itemClass="list-group-item edition-item" 
    listClass="list-group">
    <jsp:body>
        
        <div class="pull-left col-md-3 col-sm-3 col-xs-3">
            <a href="#">
                <img class="edition" src="images/placeholder.png">
            </a>
        </div>

        <div class="col-md-9 col-sm-9 col-xs-9">
            <h4 class="">
                <c:out value="${ item.ouvrage.titre }"/>
            </h4>
            
            <p>
                Editeur : 
                <c:out value="${ item.editeur.libelle }"/>
            </p>

            <p>
                Résumé : 
                <c:out value="${ item.ouvrage.resume }"/>
            </p>

            <p>
                Statut : 
                <c:out value="${ item.statut.libelle }"/>
            </p>

            <p>
                Auteur : 
                <c:out value="${ item.ouvrage.auteur.nom }"/>
            </p>

            <p>
                Quantité : 
                <c:out value="${ item.stock }"/>
            </p>

            <c:if test="${ !item.promotions.isEmpty() }">
                <p>
                    promos : 
                    <c:forEach varStatus="status" var="promotion" items="${ item.promotions }">
                        <span class="label label-default">-<c:out value="${ promotion.reduction }"/>%</span>
                    </c:forEach>
                </p>
            </c:if>
                
            <div class="btn-group btn-group-lg" role="group" aria-label="...">
                
                <a id="add" class="btn btn-success" href="shoppingcart?add=${item.isbn}">
                    <i class="glyphicon glyphicon-shopping-cart"> </i>
                </a>
                <!-- insert some href="shoppingcart?add=dollar{e.isbn}" kind of -->
                
                <c:url value="edition" var="url">
                    <c:param name="isbn" value="${ item.isbn }" />
                </c:url>
                
                <a href="${ url }" role="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-eye-open"> </i>
                </a>
                    
            </div>
        </div>

<!--        <div class="pull-right col-md-1 col-sm-1 col-xs-1">
            <div class="btn-group btn-group-vertical" role="group" aria-label="...">
                
                <a id="add" class="btn btn-success" href="shoppingcart?add=${item.isbn}">
                    <i class="glyphicon glyphicon-shopping-cart"> </i>
                </a>
                 insert some href="shoppingcart?add=dollar{e.isbn}" kind of 
                
                <%--<c:url value="edition" var="url">--%>
                    <%--<c:param name="isbn" value="${ item.isbn }" />--%>
                <%--</c:url>--%>
                
                <a href="${ url }" role="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-eye-open"> </i>
                </a>
                    
                <a role="button" class="btn btn-primary"><i class="glyphicon glyphicon-heart-empty"> </i></a>
            </div>
        </div>-->
    </jsp:body>
</t:list>