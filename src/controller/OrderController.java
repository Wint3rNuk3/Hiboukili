package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.AdressesBean;
import model.beans.ConnexionBean;
import model.beans.EditionBean;
import model.beans.OrderBean;
import model.beans.ShoppingCartBean;
import model.classes.Adresse;
import model.classes.Edition;
import model.classes.PanierTotal;
import model.classes.Utilisateur;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {
    
     private Cookie getMyCookies(Cookie[] tab, String name) {
        if (tab != null) {
            for (Cookie c : tab) {
                if (c.getName().equals(name)) {
                    return c;
                }
            }
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

       
        HttpSession session = request.getSession();
        
        String url = "/WEB-INF/jsp/RecapOrder.jsp";

        
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
////////////////////////////////////////////////////////////////////////////////
//                            Affichage du panier !                           //
////////////////////////////////////////////////////////////////////////////////

        
            EditionBean eb = new EditionBean();

            ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");

            if (cart == null) {
                cart = new ShoppingCartBean();
                session.setAttribute("cart", cart);
                cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
            }
            cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
            request.setAttribute("panierVide", cart.isEmpty());
            request.setAttribute("panier", cart.list());


        
////////////////////////////////////////////////////////////////////////////////
//                         Finalisation de la commande                        //
////////////////////////////////////////////////////////////////////////////////
            
        if ("finalOrder".equals(request.getParameter("section"))) {
            int i1 = 2;//id Utilisateur
            Cookie sync = getMyCookies(request.getCookies(), "sync");
            if (sync == null) {
                    sync = new Cookie("sync", Integer.toString(i1));
                    sync.setMaxAge(24 * 60 * 60);
                    response.addCookie(sync);
                }
            

//              OrderBean orderTotal = (OrderBean) session.getAttribute("CommandeRecap");
//              //PanierTotal panierTotal = (PanierTotal) session.getAttribute("PanierTotal");
//              PanierTotal panierTotal = new PanierTotal();
//              if(orderTotal == null){
//                  orderTotal = new OrderBean();
//                  session.setAttribute("CommandeRecap", orderTotal);
//                  //orderTotal.recupererUtilisateur();
//                  orderTotal.createPanierTotal(bc,panierTotal.getQtyTotal(), panierTotal.getPrixTotal(), panierTotal.getStatutCommande(bc));
//              }
//              orderTotal.createPanierTotal(bc,3, 50, "En cours de validation");
//              request.setAttribute("commandeVide", orderTotal.isEmpty());
//              request.setAttribute("commande", orderTotal.list());
              
              
            // afficher la commande generale 
            //   - créer une "commande" avec les elements : quantite totale, nbr d'article et statut commande
            // recuperer depuis 
            //   - deux methodes du beanPanier
            //   - methode BeanCommande : statut commande
             
            // du coup : methode créer dans commande normal .
           

            //adresse
            AdressesBean adresses = (AdressesBean) session.getAttribute("adresses");
            if (adresses == null) {
                adresses = new AdressesBean();
                session.setAttribute("adresse", adresses);

            }
            request.setAttribute("adresseVide", adresses.isEmpty());
            request.setAttribute("adresse", adresses.list());

            adresses.recupererAdresse(bc);
            System.out.println("Adresse :" + adresses.list().size());

            url = "/WEB-INF/jsp/finalOrder.jsp";

            
            if (request.getParameter("ajout") != null) {
                url = "/WEB-INF/jsp/InfosAdresse.jsp";
            }
            
            OrderBean order = (OrderBean) session.getAttribute("orderBDD");
            if (request.getParameter("final") != null && sync.getValue() != null) {
                if(order == null){
                    order = new OrderBean();
                }
                
                
                order.save(bc, Long.valueOf(request.getParameter("adresseFacturation")), 
                    Long.valueOf(request.getParameter("adresseLivraison")), Long.valueOf(sync.getValue()));
                
                url = "/WEB-INF/jsp/FormPaiement.jsp";
            }

            if (request.getParameter("retour") != null) {
                url = "/WEB-INF/jsp/RecapOrder.jsp";
            }
            
    
          
        }
////////////////////////////////////////////////////////////////////////////////
//                     PAIEMENT /VALIDATION DE COMMANDE                       //
////////////////////////////////////////////////////////////////////////////////
        
        //c'est ici qu'on envoie la commande dans la base de donnée tocarde 
        // avec controle  : if les check du paiement sont respecte alors 
        // envooe de la commande en base de donnée. 
        // dans la section précedent on sauvegarde les données necessaire ( choix adresse etc)
        
////////////////////////////////////////////////////////////////////////////////
//                            PAGE FIN COMMANDE/RETOUR ACCUEIL                //
////////////////////////////////////////////////////////////////////////////////
        
        //recuperation du numero de commande depuis SQL
        //lien hypertexte vers : 
        //              - historique commande ou moncompte
        //              - Retour Acceuil
        
        request.getRequestDispatcher(url).include(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
