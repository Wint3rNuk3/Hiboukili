<%-- 
    Document   : view
    Created on : 5 oct. 2016, 13:22:51
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>


<%-- Affichage d'une rubrique --%>
<t:template title="">

    <jsp:body>
        <div class="panel panel-default">
            <div class="panel-heading">
            </div>
            <div class="panel-body">
                <c:out value="${ rubrique }" />
                
                <c:out value="${editions.size()}" />
                
            </div>
            
            <jsp:include page="../edition/list.jsp">
                <jsp:param name="editions" value="${editions}"/>
            </jsp:include>
            
        </div>
    </jsp:body>
    
</t:template>


