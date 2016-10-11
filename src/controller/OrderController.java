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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.AdressesBean;
import model.beans.ConnexionBean;
import model.beans.EditionBean;
import model.beans.ShoppingCartBean;
import model.classes.Adresse;
import model.classes.Edition;

@WebServlet(name = "OrderController", urlPatterns = {"/OrderController"})
public class OrderController extends HttpServlet {

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
///////////////////////////////////////////////////////////////////////////////////////////////////
//                              Affichage du panier !                                            //
///////////////////////////////////////////////////////////////////////////////////////////////////

        
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


        
//////////////////////////////////////////////////////////////////////////////////////////////
//                          Finalisation de la commande                                     //
//////////////////////////////////////////////////////////////////////////////////////////////
            
        if ("finalOrder".equals(request.getParameter("section"))) {

//            
//            date 
//            SimpleDateFormat f = new SimpleDateFormat(
//                    "'On est le' dd MMMM yyyy. 'Il est' H'h'm.", Locale.FRANCE);
//            System.out.println(f);
//            COmmande
//            arraylist qui regroupe les infos de la vue et du controller !
//            

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

            if (request.getParameter("final") != null) {
                url = "/WEB-INF/jsp/FormPaiement.jsp";
            }

            if (request.getParameter("retour") != null) {
                url = "/WEB-INF/jsp/RecapOrder.jsp";
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
