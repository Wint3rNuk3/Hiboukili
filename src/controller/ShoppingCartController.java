package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.ConnexionBean;
import model.beans.EditionBean;
import model.beans.ShoppingCartBean;
import model.classes.Edition;

@WebServlet(name = "shoppingCartController", urlPatterns = {"/shoppingcart"})
public class ShoppingCartController extends HttpServlet {

    private static final String SHOPPINGCART_ROUTE = "/WEB-INF/jsp/shoppingcart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // début copié/collé
        // vérifié si un beanConnexion est enregistre ds la session; si non, le cree
        HttpSession session = request.getSession();
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
        // fin copié/collé

        // get le panier à partir de la session.
        ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");
        // creer le panier si il n'existe pas
        if (cart == null){
            cart = new ShoppingCartBean();
        }

        // trucs dont on va avoir besoin
        String prixTotal;
        final Map<String, Edition> cartMap = new HashMap<>();
        EditionBean eb = new EditionBean();
        boolean refreshed = false;
        session.setAttribute("refreshed", refreshed);
        boolean modalOpen = false;
        session.setAttribute("modalOpen", modalOpen);

        //System.out.println(cart.list());
        if (request.getParameter("add") != null) {
            //System.out.println(request.getParameter("add"));
            System.out.println(eb.findByIsbn(bc, request.getParameter("add")));
            cart.create(request.getParameter("add"), eb.findByIsbn(bc, request.getParameter("add")), 1);
            session.setAttribute("cart", cart);
            //ajouter le prix dans le shopping cart
            prixTotal = cart.getCartPrice();
            session.setAttribute("prixTotal", prixTotal);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);
        }
        if (request.getParameter("inc") != null) {
            cart.inc(request.getParameter("inc"), eb.findByIsbn(bc, request.getParameter("inc")));
            session.setAttribute("cart", cart);
            prixTotal = cart.getCartPrice();
            session.setAttribute("prixTotal", prixTotal);
            refreshed = true;
            session.setAttribute("refreshed", refreshed);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);

        }
        if (request.getParameter("dec") != null) {
            cart.dec(request.getParameter("dec"), eb.findByIsbn(bc, request.getParameter("dec")));
            session.setAttribute("cart", cart);
            prixTotal = cart.getCartPrice();
            session.setAttribute("prixTotal", prixTotal);
            refreshed = true;
            session.setAttribute("refreshed", refreshed);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);
        }
        if (request.getParameter("del") != null) {
            cart.del(request.getParameter("del"));
            session.setAttribute("cart", cart);
            prixTotal = cart.getCartPrice();
            refreshed = true;
            session.setAttribute("refreshed", refreshed);
            session.setAttribute("prixTotal", prixTotal);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);

        }
        if (request.getParameter("set") != null) {
            if (Integer.parseInt(request.getParameter("qty")) <= 0) {
                cart.del(request.getParameter("set"));
                session.setAttribute("cart", cart);
                prixTotal = cart.getCartPrice();
                session.setAttribute("prixTotal", prixTotal);
                refreshed = true;
                session.setAttribute("refreshed", refreshed);
                modalOpen = true;
                session.setAttribute("modalOpen", modalOpen);
            } else if (Integer.parseInt(request.getParameter("qty")) == cart.getInMap(request.getParameter("set")).getCartQty()) {
                refreshed = true;
                session.setAttribute("refreshed", refreshed);
                modalOpen = true;
                session.setAttribute("modalOpen", modalOpen);
            } else {
                cart.set(request.getParameter("set"), Integer.parseInt(request.getParameter("qty")));
                session.setAttribute("cart", cart);
                prixTotal = cart.getCartPrice();
                session.setAttribute("prixTotal", prixTotal);
                refreshed = true;
                session.setAttribute("refreshed", refreshed);
                modalOpen = true;
                session.setAttribute("modalOpen", modalOpen);
            }

        }
        if (request.getParameter("clean") != null) {
            cart.clean();
            session.setAttribute("cart", cart);
            prixTotal = cart.getCartPrice();
            session.setAttribute("prixTotal", prixTotal);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);
        }
        if (request.getParameter("refresh") != null) {
            refreshed = true;
            session.setAttribute("refreshed", refreshed);
            modalOpen = true;
            session.setAttribute("modalOpen", modalOpen);
        }

        // à l'arrache pour le moment
        if (request.getParameter("save") != null) {
            session.setAttribute("cart", cart);
        }
        if (request.getParameter("load") != null) {
            ShoppingCartBean cartL = (ShoppingCartBean) session.getAttribute("cart"); // faut il le mettre dans un objet ? sais pas. 
            // tu le sais toi ? moi non.
            // moi non plus alors...fait le au feeling ! 
        }

        request.getRequestDispatcher(SHOPPINGCART_ROUTE).include(request, response);

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
