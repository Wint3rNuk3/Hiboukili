package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
import model.beans.MessageBean;
import model.beans.OrderBean;
import model.beans.PaiementBean;
import model.beans.ShoppingCartBean;
import model.classes.Adresse;
import model.classes.Edition;

import model.classes.Utilisateur;

@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {

    // methode de recuperation des cookies 
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

        //creation session
        HttpSession session = request.getSession();

        // url de la page par defaut 
        String url = "/WEB-INF/jsp/RecapOrder.jsp";

        // appel du pool de connexion 
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
        // appel du cookie sync 
        int i1 = 0;//id Utilisateur
        Cookie sync = getMyCookies(request.getCookies(), "sync");
        if (sync == null) {
            sync = new Cookie("sync", Integer.toString(i1));
            sync.setMaxAge(24 * 60 * 60);
            response.addCookie(sync);
        }
        //differentes sessions ou objet utils pour le reste du code 
        AdressesBean adresses = (AdressesBean) session.getAttribute("adresses");
        OrderBean order = (OrderBean) session.getAttribute("order");
        EditionBean ed = new EditionBean();
        ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");
        MessageBean mb = (MessageBean) session.getAttribute("messages");
////////////////////////////////////////////////////////////////////////////////
//                            Affichage du panier !                           //
////////////////////////////////////////////////////////////////////////////////

        //test si le panier est nul 
        if (cart == null) {
            cart = new ShoppingCartBean();
            session.setAttribute("cart", cart);

        }

        if (request.getParameter("valid") != null) {
            url = "/WEB-INF/jsp/finalOrder.jsp";
        }

        if (request.getParameter("modif") != null) {
            url = "/WEB-INF/jsp/shoppingcart.jsp";
        }

////////////////////////////////////////////////////////////////////////////////
//                         Finalisation de la commande                        //
////////////////////////////////////////////////////////////////////////////////
        if ("finalOrder".equals(request.getParameter("section"))) {

            //affichage commande general
            if (cart != null) {

                request.setAttribute("panierVide", cart.isEmpty());
                request.setAttribute("panier", cart.list());
            }
            String prixTotal = order.calculTotal(cart.list());
            request.setAttribute("prixTotal", prixTotal);
            
            String qtyTotal = order.calculQty(cart.list());
            request.setAttribute("qtyTotal", qtyTotal);
            
            

            //adresse
            if (adresses == null) {
                adresses = new AdressesBean();
                session.setAttribute("adresse", adresses);

            }
            request.setAttribute("adresseVide", adresses.isEmpty());
            request.setAttribute("adresse", adresses.list());

            adresses.recupererAdresse(bc);

            //ajouter une adresse
            if (request.getParameter("ajout") != null) {
                url = "/WEB-INF/jsp/InfosAdresse.jsp";
            }

            //sauvegarder la commande dans la base de donnée 
            if (request.getParameter("final") != null && sync.getValue() != null) {
                if (order == null) {
                    order = new OrderBean();
                }

                order.save(bc,
                        Long.valueOf(request.getParameter("adresseFacturation")),
                        Long.valueOf(request.getParameter("adresseLivraison")),
                        Long.valueOf(sync.getValue()));
                
                if (mb == null) {
                    mb = new MessageBean();
                    session.setAttribute("messages", mb);
                }

                mb.info("Le hibou prend note de votre commande!");

                url = "/WEB-INF/jsp/FormPaiement.jsp";

            } else if (request.getParameter("retour") != null) {

            }

        }
////////////////////////////////////////////////////////////////////////////////
//                     PAIEMENT /VALIDATION DE COMMANDE                       //
////////////////////////////////////////////////////////////////////////////////
        PaiementBean pCheck = new PaiementBean();

        //check avant de payer ( ne fonctionne pas ...)
        if ("paiement".equals(request.getParameter("section"))) {
            if (request.getParameter("paye") != null) {

                if (pCheck.checkNumCarte(request.getParameter("cb")) == true) {
                    if (pCheck.checkDateExp(request.getParameter("date")) == true) {
                        if (pCheck.checkCodeCrypto(request.getParameter("crypto")) == true) {

                        }
                    }

                    url = "/WEB-INF/jsp/orderAccept.jsp";

                } else {
                    //test a remplacer par message error. 
                    System.out.println("error");
                }

                for (Edition e : cart.list()) {
                    ed.setStockInDB(bc, e);
                }
                //vider le panier une fois que la commande est faite et save dans la bdd. 
                cart.clean();

            }

            url = "/WEB-INF/jsp/orderAccept.jsp";

            if (request.getParameter("annuler") != null) {

                url = "/WEB-INF/jsp/finalOrder.jsp";
            }

        }

////////////////////////////////////////////////////////////////////////////////
//                            PAGE FIN COMMANDE/RETOUR ACCUEIL                //
////////////////////////////////////////////////////////////////////////////////
        if (order == null) {
            order = new OrderBean();
            session.setAttribute("order", order);
        }
        request.setAttribute("orderVide", order.isEmpty());
        request.setAttribute("order", order.list());

        order.recupererNumerosCommande(bc, Long.valueOf(sync.getValue()));

        if ("validation".equals(request.getParameter("section"))) {
            if (request.getParameter("monCompte") != null) {
                url = "/WEB-INF/jsp/bienvenue.jsp";
            }

            if (request.getParameter("retourA") != null) {

                url = "/WEB-INF/jsp/index.jsp";
            }

        }

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
