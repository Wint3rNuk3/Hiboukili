<%-- 
    très moche pour le moment ! a refaire plus tard. 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <table>
            <tr>
                <th>Libelle</th>
                <th>Prix unit</th>
                <th>quantité</th>
                <th>taxe</th>
                <th>promo</th>
                <th>prix total</th>
                
            </tr>
            <tr>
                <%--rempalcer les valeurs en durs par les valeurs recuperer via le panier/session grace EL--%>
                <td>livre n°1</td>
                <td>25</td>
                <td>1</td>
                <td>5.5</td>
                <td></td>
                <td>25.50 euros</td>
            </tr>  
        </table>
        <br/>
        <br/>
        <br/>
        <br/>
        
        <input type='submit' value='Modifier' name='modif'/>
        
        <input type="submit" value="Valider" name="valid"/>
        
        </form>
    </body>
</html>
