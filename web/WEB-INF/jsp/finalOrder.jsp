<%-- 
    Document   : finalisationCommande
    Created on : 4 oct. 2016, 17:05:53
    Author     : Marÿn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--<link rel="stylesheet" href="newcss.css">--%>
        <form action="finalOrder.jsp" method="post">
         
            <h1 align="center">COMMANDE</h1>
            <h2>INFORMATIONS GENERALES</h2>
            <table>
            <tr>
                <th>date</th>
                <th>total d'articles</th>
                <th>Prix total TTC</th>
                <th>Statut Commande</th>    
            </tr>
            <tr>
                <%--rempalcer les valeurs en durs par les valeurs recuperer via le panier/session grace EL--%>
                <td>01/10/2016</td>
                <td>5</td>
                <td>50 euros</td>
                <td>En cours de validation</td>
            </tr>  
        </table>
        <br/>
        <h2>CHOIX ADRESSE</h2>
        <ul>
            <li>Facturation</li>
            <table>
                <tr>
                    <td>
                        1 rue edgar poe
                    </td>
                    <td>
                        <input type ="radio" name="ok"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        52 rue des oliviers
                    </td>
                    <td>
                        <input type="radio" name ="ok"/>
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        75 rue des malades
                    </td>
                    <td>
                        <input type ="radio" name="ok"/>
                    </td>
                </tr>
            </table>
            <br/>
            <input type="submit" value="Ajouter" name="ajout"/>
            <br/>
            <br/>
            
            <li>Livraison</li>
            <table>
                <tr>
                    <td>
                        1 rue edgar poe
                    </td>
                    <td>
                        <input type ="radio" name="ok1"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        52 rue des oliviers
                    </td>
                    <td>
                        <input type="radio" name ="ok1"/>
                        
                    </td>
                </tr>
                <tr>
                    <td>
                        75 rue des malades
                    </td>
                    <td>
                        <input type ="radio" name="ok1"/>
                    </td>
                </tr>
            </table>
            <br/>
            <input type="submit" value="Ajouter" name="ajout"/>
            <br/>
        </ul>
        
        <br/>
        <br/>
        <h1 align="left">
            <input type="submit" value="Retour" name="retour" title="retournez au récapitulatif de votre commande"/>
        </h1>
        <h1 align="right">
            <input type="submit" value="Valider" name="valider" title="finalisez votre commande"/>
        </h1>
        </form>
    </body>
</html>
