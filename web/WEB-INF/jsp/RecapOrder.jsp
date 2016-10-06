<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.classes.Edition" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <jsp:useBean class="model.beans.ShoppingCartBean" id="cart" scope="session" />
        <form action="RecapOrder.jsp" method="get">
            <h1 align="center"> Recapitulatif</h1><br/>
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
                            <c:choose> 
                                <c:when test="${empty cart}">
                                     <div class="row">
                                        <div class="col-xs-2">
                                        </div>
                                        <div class="col-xs-4">
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="col-xs-4">
                                            </div>
                                            <div class="col-xs-4">
                                                <span>Panier vide !</span>
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
                                    <c:forEach items="${cart.list()}" var="e">
                                        <tr>
                                            <td class="col-md-9"><em>${e.isbn}</em></h4></td>
                                            <td class="col-md-1 text-center">${e.prixHt}</td>
                                            <td class="col-md-1 text-center">${e.cartQty}</td>
                                        </tr>


                                        <tr>
                                            <td>   </td>
                                            <td>   </td>
                                            <td class="text-right">

                                                <p>
                                                    <strong>TVA: </strong>
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
                                            <td class="text-center text-danger"><h4><strong>£31.53</strong></h4></td>
                                        </tr>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </table>

                    </div>
                </div>
            </div>
            <p align="center">
            <input type='submit' value='Modifier' name='modif'/>

            <input type="submit" value="Valider" name="valid"/>
            </p>
        </form>
    </body>
</html>
