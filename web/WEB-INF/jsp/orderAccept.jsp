

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:template title="Finalisation" imgPath="images/finalCommande.png">

    <jsp:attribute name="styles">

    </jsp:attribute>

    <jsp:body>
       
                <form action="order" method="Post">
                    
                    <div class="container">
                        <div class="row">
                            <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                                <div class="row">
                                    <div class="col-xs-6 col-sm-6 col-md-6">

                                        <address>
                                            <h1>
                                                <strong>Commande acceptée</strong>
                                            </h1>
                                            <br> 
                                        </address>
                                    </div>
                                    <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                        <p>
                                        <h5>
                                            <em>Date: 13 Octobre 2016</em>
                                        </h5>
                                        </p>

                                    </div>
                                </div>
                                <p>

                                    Félicitation ! Un hibou s'attèle actuellement à préparer votre commande ! <br/>
                                    <jsp:useBean class="model.beans.OrderBean" id="order" scope="session" />
                                    <c:choose>
                                        <c:when test="${empty order}">
                                            Commande vide !

                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${order}" var="o">

                                               Voici votre numéro de commande : ${o.numero}<br/>
                                                
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                    
                                    <br/>
                                    Vous pouvez retourner sur <a href="order?section=validation&monCompte"> votre compte</a>. <br/>
                                    <br/>

                                   

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
