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
                
            </div>
            <div class="panel-footer">
                
            </div>
        </div>
    </jsp:body>
    
</t:template>

