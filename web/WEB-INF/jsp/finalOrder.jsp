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
        <form action="OrderController" method="Post">
            <h1 align="center"> Commande Générale</h1><br/>
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
                                    <th>Nombre d'articles</th>

                                    <th class="text-center">Prix Total</th>
                                    <th class="text-center">Statut de la Commande</th>
                                </tr>
                            </thead>

                            <c:choose> 
                                <c:when test="${empty orderTotal}">
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
                                    <c:forEach items="${orderTotal}" var="p">
                                        <tr>
                                            <td class="col-md-9"><em>${p.qtyTotal}</em></h4></td>
                                            <td class="col-md-1 text-center">${p.prixTotal}</td>
                                            <td class="col-md-1 text-center">${p.statutCommande}</td>
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




                        <%-- <form action="OrderController" method="Get">

            <h1 align="center">COMMANDE</h1>
            <h3>INFORMATIONS GENERALES</h3>
            <fieldset>
                <table>
                    <tr>
                        <th>date</th>
                        <th>total d'articles</th>
                        <th>Prix total TTC</th>
                        <th>Statut Commande</th>    
                    </tr>
                    <tr>
                        <%--rempalcer les valeurs en durs par les valeurs recuperer via le panier/session grace EL--%>
                        <%--  <td>01/10/2016</td>
                          <td>5</td>
                          <td>50 euros</td>
                          <td>En cours de validation</td>
                      </tr>  
                  </table>
              </fieldset>--%>
                        <%--ADREEEESSSSSSSSSSSSSSSSSSSSSSEEEEEEEEEEEEEEEEEEEE--%>
                        <br/>      
                        <br/>
                        <h4>CHOIX ADRESSE</h4>
                        <fieldSet>
                            <legend>Adresse de facturation</legend><br/>
                            <br/>
                            <br/>
                            <c:choose>
                                <c:when test="${empty adresse}">
                                    Adresse vide !

                                </c:when>
                                <c:otherwise>
                                    <select name="adressefacturation">
                                        <c:forEach items="${adresse}" var="a">
                                            <option value="${a.id}">${a.numero},${a.voie},${a.cp},${a.ville},${a.complement}</option>
                                        </c:forEach>
                                    </select> 
                                </c:otherwise>
                            </c:choose>
                        </fieldSet>
                        <br/>
                        <div align="right">
                            <a href="OrderController?section=finalOrder&ajout">Ajouter une nouvelle adresse</a>

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
                                            <option value="${a.id}">${a.numero},${a.voie},${a.cp},${a.ville},${a.complement}</option>
                                        </c:forEach>
                                    </select> 
                                </c:otherwise>
                            </c:choose>
                        </fieldset>
                        <br/>
                        <div align="right">
                            <a href="OrderController?section=finalOrder&ajout">Ajouter une nouvelle adresse</a>
                        </div>
                        <br/>
                        <br/>
                        <a href="OrderController?section=finalOrder&final">Valider</a>

                        <br/>
                        <br/>
                        <a href="OrderController?section=finalOrder&retour">Retour</a>

                        <br/>
                        <br/>
                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
