package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
        EditionBean eb = new EditionBean();
//        if ("panier".equals(request.getParameter("section"))) {
//            ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");
//            if (cart == null) {
//                cart = new ShoppingCartBean();
//                cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
//                session.setAttribute("cart", cart);
//
//            }
//
//            
//            request.setAttribute("panierVide", cart.isEmpty());
//            request.setAttribute("panier", cart.list());
//
//        } else {
            ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");
            if (cart == null) {
                cart = new ShoppingCartBean();
                session.setAttribute("cart", cart);
                cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
            }
            request.setAttribute("panierVide", cart.isEmpty());
            request.setAttribute("panier", cart.list());
            
            
            System.out.println(">>>>>>>>>>>>"+ cart.list().size());
        
        
//        if("commande".equals(request.getParameter("section"))){
//            if(request.getParameter("modif")!= null){
//                url="/WEB-INF/jsp/shoppingcart.jsp";
//                
//            }
//            
//            if(request.getParameter("valid")!= null){
//                url="/WEB-INF/jsp/finalOrder.jsp";
//            }
//        }

        //controller pour les boutons "modifier" et " valider"
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
