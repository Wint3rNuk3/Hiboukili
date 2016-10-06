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
    itemClass="col-md-12 col-sm-12 col-xs-12 media thumbnail edition-item" 
    listClass="media-list">
    <jsp:body>
        
        <div class="media-left pull-left col-md-3 col-sm-3 col-xs-3">
            <a href="#">
                <img class="edition media-object" src="images/placeholder.png">
            </a>
        </div>

        <div class="media-body col-md-7 col-sm-7  col-xs-7">
            <h4 class="media-heading">
                <c:out value="${ item.ouvrage.titre }"/>
            </h4>

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
        </div>

        <div class="media-right col-md-2 col-sm-2  col-xs-2">
            <div class="pull-right btn-group btn-group-vertical" role="group" aria-label="...">
                
                <a role="button" class="btn btn-success" href="#cartModal" data-toggle="modal" data-target="#cartModal">
                    <!-- insert some href="shoppingcart?inc=dollar{e.isbn}" kind of -->
                    <i class="glyphicon glyphicon-shopping-cart"> </i>
                </a>
                
                <c:url value="edition" var="url">
                    <c:param name="isbn" value="${ item.isbn }" />
                </c:url>
                
                <a href="${ url }" role="button" class="btn btn-primary">
                    <i class="glyphicon glyphicon-eye-open"> </i>
                </a>
                    
                <a role="button" class="btn btn-primary"><i class="glyphicon glyphicon-heart-empty"> </i></a>
            </div>
        </div>
    </jsp:body>
</t:list>