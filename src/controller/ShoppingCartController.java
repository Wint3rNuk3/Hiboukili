package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.ShoppingCartBean;

@WebServlet(name = "shoppingCartController", urlPatterns = {"/shoppingCartController"})
public class ShoppingCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        
        String url = "/WEB-INF/jsp/shoppingcart.jsp";
        
        
        HttpSession session = request.getSession();
        // get le panier à partir de la session.
        ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("ShoppingCart");
        

        // Si l'utilisateur n'a pas de panier, le creer.
        if (cart == null) {
            cart = new ShoppingCartBean();
            session.setAttribute("ShoppingCart", cart);
        }

        if (request.getParameter("add") != null) {
            cart.create(request.getParameter("add"));
        }
        if (request.getParameter("inc") != null) {
            cart.inc(request.getParameter("inc"));
        }
        if (request.getParameter("dec") != null) {
            cart.dec(request.getParameter("dec"));
        }
        if (request.getParameter("del") != null) {
            cart.del(request.getParameter("del"));
        }
        if (request.getParameter("clean") != null) {
            cart.clean();
        }
        
        
        
        // à l'arrache pour le moment
        
        if(request.getParameter("save") != null){
            session.setAttribute("ShoppingCart", cart);
        }
        if(request.getParameter("load") != null){
            ShoppingCartBean cartL = (ShoppingCartBean) session.getAttribute("ShoppingCart"); // faut il le mettre dans un objet ? sais pas. 
                                                                                              // tu le sais toi ? moi non.
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
