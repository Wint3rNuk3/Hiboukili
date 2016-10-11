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
                <!-- je le mets là en attendant pour que ça fonctionne partout, 
                je sais que ça serait mieux d'en faire une page à part pour le
                moment, mais j'ai peur de tout casser en essayant de retirer
                la modale -->
                <!-- note, quand on clique sur le bouton d'achat correspondant à une edition
                dans une rubrique, on retourne sur la page de l'index (qui est la même que celle de shopping cart)
                assez normal en fait... vu le bidouillage. -->
                <%--<c:import url="../../jsp/shoppingcart.jsp"/>--%>
            
        </div>
    </jsp:body>
    
</t:template>


