<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <form action="order" method="Post">
            <h1 align="center"> Commande Générale</h1><br/>
            <br/>
            <div class="container">
                <div class="row">
                    <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <address>
                                    <h1>
                                    <strong>Hibookili</strong>
                                    </h1>
                                    <br> 
                                </address>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                <p>
                                <h5>
                                    <em>Date: 6 Octobre 2016</em>
                                </h5>
                                </p>

                            </div>
                        </div>

                        </span>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Nombre d'articles</th>

                                    <th class="text-center">Prix Total</th>
                                    <th class="text-center">Statut de la Commande</th>
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
                                                <span>Commande vide!</span>
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
                                    <c:forEach items="${cart.list()}" var="c">
                                        <tr>
                                            <td class="col-md-9"><em>${c.cartQty}</em></h4></td>
                                            <td class="col-md-1 text-center">${c.getPrix() * c.getCartQty()}</td>
                                            <td class="col-md-1 text-center">En cours de validation</td>
                                        </tr>


                                        <%--<tr>
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
                                        </tr>--%>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>

                        </table>


                        <br/>      
                        <br/>
                        <h5>CHOIX ADRESSE</h5>
                        <fieldSet>
                            <legend>Adresse de facturation</legend><br/>
                            <br/>
                            <br/>
                            <c:choose>
                                <c:when test="${empty adresse}">
                                    Adresse vide !

                                </c:when>
                                <c:otherwise>
                                    <select name="adresseFacturation">
                                        <c:forEach items="${adresse}" var="a">
                                            <option value="${a.id}">${a.numero},${a.voie},${a.cp},${a.ville}</option>
                                        </c:forEach>
                                    </select> 
                                </c:otherwise>
                            </c:choose>
                        </fieldSet>
                        <br/>
                        <div align="right">
                            <a href="order?section=finalOrder&ajout">Ajouter une nouvelle adresse</a>

                        </div>
                        <br/>
                        <fieldset>
                            <legend>Adresse de livraison</legend><br/>
                            <br/>
                            <br/>
                            <c:choose>
                                <c:when test="${empty adresse}">
                                    Adresse vide !

                                </c:when>
                                <c:otherwise>
                                    <select name="adresseLivraison">
                                        <c:forEach items="${adresse}" var="a">
                                            <option value="${a.id}">${a.numero},${a.voie},${a.cp},${a.ville}</option>
                                        </c:forEach>
                                    </select> 
                                </c:otherwise>
                            </c:choose>
                        </fieldset>
                        <br/>
                        <div align="right">
                            <a href="order?section=finalOrder&ajout">Ajouter une nouvelle adresse</a>
                        </div>
                        <br/>
                        <br/>
                            <input type='submit' name='doIt' value='Valider'>
                            <input type="hidden" name="section" value="finalOrder" />
                            <input type="hidden" name="final" value="" />
                        <%--<a href="OrderController?section=finalOrder&final">Valider</a>--%>

                        <br/>
                        <br/>
                        <a href="order?section=finalOrder&retour">Retour</a>

                        <br/>
                        <br/>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
