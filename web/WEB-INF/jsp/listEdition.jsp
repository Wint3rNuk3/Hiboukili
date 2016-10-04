<%-- 
    Document   : listEdition
    Created on : 4 oct. 2016, 16:20:56
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
