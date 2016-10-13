

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Hiboukilit">

    <jsp:attribute name="styles">

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
    </jsp:attribute>

    <jsp:body>
        <html>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>
            </head>
            <body>
                <form action="order" method="Post">
                    <h1 align="center"> COMMANDE ACCEPTEE</h1>
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
                                <p>

                                    Voici les informations relatives à votre commande!<br/>
                                    <c:choose>
                                        <c:when test="${empty order}">
                                            Commande vide !

                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${order}" var="o">

                                                Numero de commande : ${o.numero}<br/>
                                                
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    <%--recuperation du numero de commande via methode SQL bean commande--%>
                                    <br/>
                                    Vous pouvez retourner sur votre compte. <br/>
                                    <br/>

                                    Dans la rubrique <a href="order?section=validation&monCompte">Mon Compte</a>.<br/>

                                    <br/>
                                </p>


                                <input type="submit" name="retourA" value="Retour Accueil"/><br/>
                                <input type="hidden" name="section" value="validation"/>
                                <input type="hidden" name="retourA" value=""/>
                                <br/>
                                <br/>




                            </div>
                        </div>
                    </div>

                </form>
            </body>
        </html>
    </jsp:body>
</t:template>
