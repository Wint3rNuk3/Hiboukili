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
        
        //creation session
        HttpSession session = request.getSession();
        //url page par defaut 
        String url = "/WEB-INF/jsp/RecapOrder.jsp";
        
        //appel du pool de connexion 
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
        
        //Section Recap Order
        EditionBean eb = new EditionBean();

            ShoppingCartBean cart = (ShoppingCartBean) session.getAttribute("cart");
            //test : si le panier est nul en crée un . 
            if (cart == null) {
                cart = new ShoppingCartBean();
                session.setAttribute("cart", cart);
                cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
            }
            cart.create("978-2-0001-0001-0", eb.findByIsbn(bc, "978-2-0001-0001-0"));
            request.setAttribute("panierVide", cart.isEmpty());
            request.setAttribute("panier", cart.list());
            
            //test 
//            System.out.println(">>>>>>>>>>>>"+ cart.list().size());
        
        

        if(request.getParameter("valid") != null){
            url="/WEB-INF/jsp/finalOrder.jsp";
        }
        
        if(request.getParameter("modif") != null){
            url="/WEB-INF/jsp/shoppingcart.jsp";
        }
        
        
        //section FInal Order
        //if("finalOrder".equals(request.getParameter("section"))){
            if(request.getParameter("valid")!= null){
                AdressesBean adresses=(AdressesBean) session.getAttribute("adresses");
                if(adresses==null){
                    adresses=new AdressesBean();
                    session.setAttribute("adresse", adresses);
                    
                }
                request.setAttribute("adresseVide", adresses.isEmpty());
                request.setAttribute("adresse", adresses.list());
                
                //adresses.recupererAdresse(bc);
                System.out.println("Adresse :"+adresses.list().size());
                
                url="/WEB-INF/jsp/finalOrder.jsp";
            }
       // }
        

        
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
