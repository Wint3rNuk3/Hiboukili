<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="css/cssFormPaiement.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <form action="order" method="Post">

            <h1 align="center">COORDONNEES DE PAIEMENT</h1>
            <br/>
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
                        Numero CB : <input type="number" name="cb" required/><br/>
                        <br/>
                        Date d'expiration : <input type="date" name="date" required/><br/>
                        <br/>
                        Code de securité: <input type="number" name="crypto" title="Correspond aux 3 chiffres visibles au dos de votre carte " required/><br/>
                        <br/>
                        Nom du porteur :<input type="text" name="nom" required/><br/>
                        <br/>
                        <br/>
                        <br/>
                        <p>
                            choisissez le type de votre carte :
                            <br/>
                            <br/>
                        <section class="panel-image" id="pan-img">
                            <div class="conteneurImg">
                                <div class="imgCarte">
                                    <img src="images/visa.png" alt="picto carte visa"/>
                                    <input type="radio" name="carte"/>
                                </div>
                                <div class="imgCarte">
                                    <img src="images/amEx.png" alt="picto carte american express"/>
                                    <input type="radio" name="carte"/>
                                </div>
                                <div class="imgCarte">
                                    <img src="images/masterCard.png" alt="picto carte masterCard"/>
                                    <input type="radio" name="carte"/>
                                </div>
                                <div class="imgCarte">
                                    <img src="images/carteBleu.png" alt="picto carte bleu"/>
                                    <input type="radio" name="carte"/>
                                </div>
                            </div>
                        </section>


                        </p>

                        <br/>
                        <br/>
                        <div class="conteneurBouton">
                            <div align="right" class="boutonAnnuler">
                                <input type="submit" name="annuler" value="Annuler"/><br/>
                            </div>
                            <br/>
                            <div align ="left" class="boutonValider">
                                <input type="submit" name="valider" value="Payer"/><br/>
                            </div>


                        </div>




                    </div>
                </div>
            </div>

        </form>
    </body>
</html>
