<%-- 
    Document   : index
    Created on : 3 oct. 2016, 16:05:02
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Hiboukili">

    <jsp:attribute name="styles">
    </jsp:attribute>

    <jsp:attribute name="scripts">
    </jsp:attribute>

    
    
    <jsp:body>
        <div class="col-lg-12">
            
            <div class="text-center">

                <!-- formulaire de recherche -->
                <form class="form-inline">

                    <div class="form-group">
                        <div class="input-group">
                            <input type="text" class="form-control" id="recherche" placeholder="Votre recherche">
                            <div class="input-group-btn">
                                <button type="submit" class="btn btn-default" >
                                    <i class="glyphicon glyphicon-search"> </i>
                                </button>
                            </div>
                        </div>
                    </div>
                </form>
            </div>

            <hr>

            <c:import url="edition/list.jsp"/>
            
            <%-- attention ton code rajoute 20px de margin top au body ! --%>
            <%-- <c:import url="../jsp/shoppingcart.jsp"/> --%>

        </div>
    </jsp:body>
</t:template>
