<%-- 
    Document   : listEdition
    Created on : 4 oct. 2016, 16:20:56
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>


<t:template title="Promotions">
    <jsp:body>
        <t:list 
            var="item"
            list="${ promotions }" 
            page="" 
            nbPage="" 
            perPage="" 
            paginationUrl="" 
            itemClass="list-group-item" 
            listClass="list-group">
            <jsp:body>
                 <div class="pull-left col-md-3 col-sm-3 col-xs-3">
                    <a href="#">
                        <img class="img img-thumbnail" src="images/<c:out value="${ item.image }" default="placeholder.png"/>">
                    </a>
                </div>

                <div class="col-md-9 col-sm-9 col-xs-9">
                    <c:out value="${ item.description }"/>
                    <c:out value="${ item.commentaire }"/>
                </div>
            </jsp:body>
        </t:list>
    </jsp:body>
    
</t:template>
