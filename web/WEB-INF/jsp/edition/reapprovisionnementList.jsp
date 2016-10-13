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

        <div class="col-md-7 col-sm-7 col-xs-7">
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
        </div>

        <div class="pull-right col-md-2 col-sm-2  col-xs-2">
            <div class="btn-group" role="group" aria-label="...">
                
                <c:url value="reapprovisionnement" var="url">
                    <c:param name="iwantmoaarr" value="${ item.isbn }" />
                </c:url>
                
                <form id="form" action="${url}" method="post" style="position:relative;">
                    <input type="number" class="form-control input-sm" name="qty" style="width:50%">
                    <button type="submit" formaction="${url}" class="btn btn-primary" style="postion:absolute">
                        <i class="glyphicon glyphicon-plane"> </i>
                    </button>
                </form>
                
                        
                

                


            </div>
        </div>
    </jsp:body>
</t:list>