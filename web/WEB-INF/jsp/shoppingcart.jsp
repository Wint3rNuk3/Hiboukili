<%-- 
    Document   : shoppingcart
    Created on : 4 oct. 2016, 09:02:49
    Author     : Michael Carrasco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.classes.Edition" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>


        <!-- import de bootstrap -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">

    </head>

    <body>
        <jsp:useBean class="model.beans.ShoppingCartBean" id="cart" scope="session" />
        <%--<c:if test="${modalOpen}">
            <script src="js/vendor/jquery-1.11.2.min.js"></script>
            <script>
                $(document).ready(function () {
                    $('.row').removeClass('fade');
                    $('#cartModal').modal('toggle');
                });
            </script>
        </c:if>--%>
        <!--fade modal--><div class="row  " id="cartModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="panel panel-info modal-content">
                    <div class="panel-heading modal-header">
                        <div class="panel-title">
                            <div class="row">
                                <div class="col-xs-6">
                                    <h5 class="modal-title"><span class="glyphicon glyphicon-shopping-cart"></span> Panier d'achats</h5>
                                </div>
                                <div class="col-xs-6">
                                    <button type="button" class="btn btn-primary btn-sm btn-block" onclick="window.location.href='/Hiboukili'" type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="glyphicon glyphicon-share-alt"></span> Continuer les achats
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-body modal-body">
                        <c:choose>
                            <c:when test="${cart.list().size() == 0}">
                                <div class="row">
                                    <div class="col-xs-2">
                                    </div>
                                    <div class="col-xs-4">
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="col-xs-4">
                                        </div>
                                        <div class="col-xs-4">
                                            <h4>Panier vide !</h4>
                                        </div>
                                        <div class="col-xs-2">          
                                        </div>
                                        <div class="col-xs-2">
                                        </div>
                                    </div>
                                </div>
                                <hr>
                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${ cart.list() }" var="e">
                                    <div class="row">
                                        <div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
                                        </div>
                                        <div class="col-xs-4">
                                            <h4 class="product-name"><strong>${e.ouvrage.titre}</strong></h4><h4><small>${e.ouvrage.resume}</small></h4>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="col-xs-4 text-right" style="margin-top:10px;">
                                                <h6><strong>${ e.prixHt } HT <span class="text-muted">x</span></strong></h6>
                                            </div>
                                            <div class="col-xs-4" style="margin-top:10px;">
                                                <form action="${pageContext.request.contextPath}/shoppingcart?set=${e.isbn}" method="post"><input type="text" class="form-control input-sm" name="qty" value="${e.cartQty}"></form>
                                            </div>
                                            <div class="col-xs-2" style="margin-top:5px;">
                                                <a type="button" class="btn btn-success btn-xs" id="inc" href="shoppingcart?inc=${e.isbn}" style="font-size:10px; width:18px; height:18px;" data-backdrop="static">+</a>
                                                <a type="button" class="btn btn-danger btn-xs" id="dec" href="shoppingcart?dec=${e.isbn}" style="font-size:10px; width:18px; height:18px;">
                                                    <p style="margin-top: -1px; margin-left: -1px">-</p>
                                                </a>                
                                                <!-- ICI -->
                                                <!--<a href="jspPanier.jsp?clean">Vider le panier</a>-->          
                                            </div>
                                            <div class="col-xs-2" style="margin-top:15px;">
                                                <a type="button" href="shoppingcart?del=${e.isbn}" class="btn btn-link btn-xs">
                                                    <span class="glyphicon glyphicon-trash"> </span>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                        <div class="row">
                            <div class="text-center">
                                <div class="col-xs-3">
                                    <a type="button" class="btn btn-danger btn-sm btn-block" id="clean" href="shoppingcart?clean=true" style="margin-bottom:4px; white-space: normal;"> Vider le panier</a>
                                </div>
                                <div class="col-xs-6">
                                    <h6 class="text-right">Changements dans le panier ?</h6>
                                </div>
                                <div class="col-xs-3">
                                    <a type="button" class="btn btn-default btn-sm btn-block" id="refresh" href="shoppingcart?refresh=true" style="margin-bottom:4px; white-space: normal;">
                                        Mettre à jour le panier
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="panel-footer modal-footer">
                        <div class="row text-center">
                            <div class="col-xs-9">
                                <h4 class="text-right">Total <strong>${prixTotal} €</strong></h4>
                            </div>
                            <div class="col-xs-3">
                                <button type="button" class="btn btn-success btn-block" style="margin-bottom:4px; white-space: normal;">
                                    Valider
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
