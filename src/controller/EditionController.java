package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.ConnexionBean;
import model.beans.EditionBean;
import model.classes.Edition;

@WebServlet(name = "EditionController", urlPatterns = {"/edition"})
public class EditionController extends HttpServlet {
    
    private static final String VIEW_ROUTE = "/WEB-INF/jsp/edition/view.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // début copié/collé
        // vérifié si un beanConnexion est enregistre ds la session; si non, le cree 
        HttpSession session = request.getSession();           
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
        // fin copié/collé
        
        if(request.getParameter("isbn") != null){
            Edition edition = new EditionBean().findByIsbn(bc, request.getParameter("isbn"));
            request.setAttribute("edition", edition);
            // on fait suivre à la jsp de visualisation.
            getServletContext()
                    .getRequestDispatcher(VIEW_ROUTE)
                    .forward(request, response);
        }
        
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
