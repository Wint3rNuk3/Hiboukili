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
            <c:url value="edition" var="url">
                <c:param name="isbn" value="${ item.isbn }" />
            </c:url>
            <a href="${ url }">
                <img class="edition" src="images/placeholder.png">
            </a>
        </div>

        <div class="col-md-9 col-sm-9 col-xs-9 clearfix">
            <h4 class="">
                <c:out value="${ item.ouvrage.titre }" />
                de
                <c:out value="${ item.ouvrage.auteur.prenom }"/> 
                <c:out value="${ item.ouvrage.auteur.nom }"/>
                (<c:out value="${ item.datePublication}" />)

                <c:if test="${ !empty item.promotions }">
                    <c:forEach varStatus="status" var="promotion" items="${ item.promotions }">
                        <span class="label label-success">-<c:out value="${ promotion.reduction }"/>%</span>
                    </c:forEach>
                </c:if>
                <span class="label label-info"><c:out value="${ item.statut.libelle }"/></span>
                
            </h4>
                
                
            <a id="add" class="pull-right btn btn-success" href="shoppingcart?add=${item.isbn}">
                <i class="glyphicon glyphicon-shopping-cart"> </i>
            </a>
            
            <p>
                Editeur : 
                <c:out value="${ item.editeur.libelle }"/>
            </p>

            <p>
                Résumé : 
                <c:out value="${ item.ouvrage.resume }"/>
            </p>

            <p>
                il nous en reste : <c:out value="${ item.stock }"/>
            </p>

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