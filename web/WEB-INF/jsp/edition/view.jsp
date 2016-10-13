<%-- 
    Document   : view
    Created on : 5 oct. 2016, 13:22:37
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template title="${ edition.ouvrage.titre }">

    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-heading clearfix">
                <h4>
                    <c:out value="${ edition.ouvrage.titre }" />
                    de
                    <c:out value="${ edition.ouvrage.auteur.prenom }"/> 
                    <c:out value="${ edition.ouvrage.auteur.nom }"/>
                    (<c:out value="${ edition.datePublication}" />)
                    
                    <c:if test="${ !edition.promotions.isEmpty() }">
                        <c:forEach varStatus="status" var="promotion" items="${ edition.promotions }">
                            <span class="label label-success">-<c:out value="${ promotion.reduction }"/>%</span>
                        </c:forEach>
                    </c:if>
                    <span class="label label-info"><c:out value="${ edition.statut.libelle }"/></span>

                    <a id="add" class="pull-right btn btn-success" href="shoppingcart?add=${item.isbn}">
                        <i class="glyphicon glyphicon-shopping-cart"> </i>
                    </a>

                </h4>
            </div>
            <div class="panel-body">
                
                <img src="" />
                
                <p>
                    Résumé : 
                    <c:out value="${ edition.ouvrage.resume }"/>
                </p>

                <p>
                    Il en reste <c:out value="${ edition.stock }"/> !
                </p>

            </div>
                
            <div class="panel-footer">
                
            </div>
        </div>
    </jsp:body>
    
</t:template>

