<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.classes.Edition" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--<link rel="stylesheet" href="newcss.css"/>--%>
        <form action="RecapOrder.jsp" method="get">
            <h1 align="center"> Commande </h1><br/>
            <br/>
            <h2 align="left"> Recapitulatif</h2><br/>
            <br/>
            
            <div class="container">
    <div class="row">
        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                        <strong>Hibookili</strong>
                        <br> 
                    </address>
                </div>
                <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                    <p>
                        <em>Date: 6 Octobre 2016</em>
                    </p>
                    
                </div>
            </div>
            
                </span>
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Produit</th>
                            
                            <th class="text-center">PrixHt</th>
                            <th class="text-center">Quantité</th>
                        </tr>
                    </thead>
                    <tbody>
                        <jsp:useBean class="model.beans.ShoppingCartBean" id="cart" scope="session" />
                        <c:choose> 
                            <c:when test="${empty cart}">

                            <h1> Panier vide !</h1>

                            </c:when>
                            <c:otherwise>
                                <c:forEach items="${cart.list()}" var="e">
                                    <tr>
                                        <td class="col-md-9"><em>${cart.save()}</em></h4></td>
                                        <td class="col-md-1" style="text-align: center"> 2 </td>
                                       <%-- <td class="col-md-1 text-center">${e.cartQty}</td>
                                        <td class="col-md-1 text-center">${e.prixHt}</td>--%>
                                    </tr>
                                    
                                    
                                    <tr>
                                    <td>   </td>
                                    <td>   </td>
                                        <td class="text-right">
                                       
                                         <p>
                                            <strong>Tax: </strong>
                                        </p></td>
                                        <td class="text-center">
                                        <p>
                                            <strong>5.5%</strong>
                                        </p>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td>   </td>
                                    <td>   </td>
                                        <td class="text-right"><h4><strong>Total: </strong></h4></td>
                                        <td class="text-center text-danger"><h4><strong>$31.53</strong></h4></td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
                
            </div>
        </div>
    </div>
<%--            

                
                
                        <div class="row">
                            <div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
                            </div>
                            <div class="col-xs-4">
                                <h4 class="product-name"><strong>${e.ouvrage.titre}</strong></h4><h4><small>${e.ouvrage.resume}</small></h4>
                            </div>
                            <div class="col-xs-6">
                                <div class="col-xs-4 text-right" style="margin-top:10px;">
                                    <h6><strong>${ e.prixHt } <span class="text-muted">x</span></strong></h6>
                                </div>
                                <div class="col-xs-4" style="margin-top:10px;">
                                    <input type="text" class="form-control input-sm" value="${e.cartQty}">
                                </div>
                                <div class="col-xs-2" style="margin-top:5px;">
                                    <a type="button" class="btn btn-success btn-xs" href="shoppingcart?inc=${e.isbn}" style="font-size:10px; width:18px; height:18px;">+</a>                <!-- ICI -->
                                    <a type="button" class="btn btn-danger btn-xs" href="shoppingcart?dec=${e.isbn}" style="font-size:10px; width:18px; height:18px;">
                                        <p style="margin-top: -1px; margin-left: -1px">-</p>
                                    </a>                <!-- ICI -->
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
            </p>
            <br/>
            <br/>
            <br/>
            <br/>--%>

            <input type='submit' value='Modifier' name='modif'/>

            <input type="submit" value="Valider" name="valid"/>

        </form>
    </body>
</html>
