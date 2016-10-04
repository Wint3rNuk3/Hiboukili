<%-- 
    Document   : shoppingcart
    Created on : 4 oct. 2016, 09:02:49
    Author     : Michael Carrasco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body style="margin-top: 20px;">
        <div class="container">
            <div class="row">
                <div class="col-xs-8">
                    <div class="panel panel-info">
                        <div class="panel-heading">
                            <div class="panel-title">
                                <div class="row">
                                    <div class="col-xs-6">
                                        <h5><span class="glyphicon glyphicon-shopping-cart"></span> Panier d'achats</h5>
                                    </div>
                                    <div class="col-xs-6">
                                        <button type="button" class="btn btn-primary btn-sm btn-block">
                                            <span class="glyphicon glyphicon-share-alt"></span> Continuer les achats
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
                                </div>
                                <div class="col-xs-4">
                                    <h4 class="product-name"><strong>Nom de l'édition</strong></h4><h4><small>Description de l'édition</small></h4>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-4 text-right" style="margin-top:10px;">
                                        <h6><strong>25.00 <span class="text-muted">x</span></strong></h6>
                                    </div>
                                    <div class="col-xs-4" style="margin-top:10px;">
                                        <input type="text" class="form-control input-sm" value="1">
                                    </div>
                                    <div class="col-xs-2" style="margin-top:5px;">
                                        <button type="button" class="btn btn-success btn-xs" style="font-size:10px; width:18px; height:18px;">+</button>                <!-- ICI -->
                                        <button type="button" class="btn btn-danger btn-xs" style="font-size:10px; width:18px; height:18px;">
                                            <p style="margin-top: -1px; margin-left: -1px">-</p>
                                        </button>                <!-- ICI -->
                                    </div>
                                    <!--  text-align:center;  margin-right:-20px; -->
                                    <div class="col-xs-2" style="margin-top:15px;">
                                        <button type="button" class="btn btn-link btn-xs">
                                            <span class="glyphicon glyphicon-trash"> </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-xs-2"><img class="img-responsive" src="http://placehold.it/100x70">
                                </div>
                                <div class="col-xs-4">
                                    <h4 class="product-name"><strong>Nom de l'édition</strong></h4><h4><small>Description de l'édition</small></h4>
                                </div>
                                <div class="col-xs-6">
                                    <div class="col-xs-4 text-right" style="margin-top:10px;">
                                        <h6><strong>25.00 <span class="text-muted">x</span></strong></h6>
                                    </div>
                                    <div class="col-xs-4" style="margin-top:10px;">
                                        <input type="text" class="form-control input-sm" value="1">
                                    </div>
                                    <div class="col-xs-2" style="margin-top:5px;">
                                        <button type="button" class="btn btn-success btn-xs" style="font-size:10px; width:18px; height:18px;">+</button>                <!-- ICI -->
                                        <button type="button" class="btn btn-danger btn-xs" style="font-size:10px; width:18px; height:18px;">
                                            <p style="margin-top: -1px; margin-left: -1px">-</p>
                                        </button>                <!-- ICI -->
                                    </div>
                                    <div class="col-xs-2" style="margin-top:15px;">
                                        <button type="button" class="btn btn-link btn-xs">
                                            <span class="glyphicon glyphicon-trash"> </span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="text-center">
                                    <div class="col-xs-9">
                                        <h6 class="text-right">Changements dans le panier ?</h6>
                                    </div>
                                    <div class="col-xs-3">
                                        <button type="button" class="btn btn-default btn-sm btn-block" style="margin-bottom:4px; white-space: normal;">
                                            Mettre à jour le panier
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <div class="row text-center">
                                <div class="col-xs-9">
                                    <h4 class="text-right">Total <strong>€50.00</strong></h4>
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
        </div>
    </body>
</html>
