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
            <div class="panel-heading">
                <c:out value="${ edition.ouvrage.titre }" />
            </div>
            <div class="panel-body">
                
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

                <c:if test="${ !edition.promotions.isEmpty() }">
                    <p>
                        promos : 
                        <c:forEach varStatus="status" var="promotion" items="${ edition.promotions }">
                            <span class="label label-default">-<c:out value="${ promotion.reduction }"/>%</span>
                        </c:forEach>
                    </p>
                </c:if>
            </div>
            <div class="panel-footer">
                
            </div>
        </div>
    </jsp:body>
    
</t:template>

