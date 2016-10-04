package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.BeanConnexion;
import model.beans.EditionBean;
import model.classes.Edition;

@WebServlet(name = "IndexController", urlPatterns = {""})
public class IndexController extends HttpServlet {
    
    private static final String INDEX_ROUTE = "/WEB-INF/jsp/index.jsp";

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
           
    }

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
  
        // c'est du copié/collé, car on refuse de reutiliser les DAO,
        // et que je n'ai pas le droit de coder.
        
        // début copié/collé
        // vérifié si un beanConnexion est enregistre ds la session; si non, le cree 
        HttpSession session = request.getSession();           
        BeanConnexion bc = (BeanConnexion) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new BeanConnexion();
            session.setAttribute("sessionConnexion", bc);
        }
        // fin copié/collé
        
        List<Edition> editions = new EditionBean().findAll(bc);
        
        int perPage = 6;
        
        if(request.getParameter("perPage") != null) {
            perPage = Integer.parseInt(request.getParameter("perPage"));
        }
        
        request.setAttribute("perPage", perPage);
        
        int nbPage = (int)Math.ceil((double) editions.size() / perPage);
        request.setAttribute("nbPage", nbPage);
        
        Integer page = 1;
        
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
            
            if(page > nbPage) {
                // Si la page demandée excede le nombre de page,
                // allons a la derniere page.
                page = nbPage;
            }
        }
        
        request.setAttribute("page", page);
        
        List displayed = editions.subList(
                Math.max(page * perPage - perPage, 0), 
                Math.min(page * perPage, editions.size()));
        
        request.setAttribute("editions", displayed);
        
        getServletContext()
                .getRequestDispatcher(INDEX_ROUTE)
                .forward(request, response);
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
