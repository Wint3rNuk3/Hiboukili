<%-- 
    Document   : index
    Created on : 3 oct. 2016, 16:05:02
    Author     : cdi305
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js" lang=""> <!--<![endif]-->
    
    <c:import url="template/head.jsp"/>
        
    <body>

        <c:import url="template/browserAlert.jsp"/>
        
        <c:import url="template/navbar.jsp"/>

        <%-- Application container --%>
        <section class="container-fluid">

            <div class="row">

                <%-- left-nav --%>
                <div class="col-md-3 col-sm-3 col-xs-3">
                    <c:import url="template/leftMenu.jsp"/>
                </div><%-- Fin left-nav --%>

                <%-- Colonne centrale --%>
                <div class="col-md-9 col-sm-9 col-xs-9">

                    <div class="row text-center">

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

                    <c:import url="listEdition.jsp"/>
                    
                </div><%-- Fin colonne centrale --%>
            </div><%-- Fin row --%>

            <c:import url="template/footer.jsp"/>

        </section> <!-- /container -->        

        <c:import url="template/scripts.jsp"/>
    </body>
</html>

