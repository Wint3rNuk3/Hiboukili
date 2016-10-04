package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.classes.Edition;

@WebServlet(name = "IndexController", urlPatterns = {""})
public class IndexController extends HttpServlet {
    
    private static final String INDEX_ROUTE = "/WEB-INF/jsp/index.jsp";

    // temporaire en attendant les acces bdd.
    private List<Edition> editions = new ArrayList();
    
    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
              
        // On construit une liste d'edition en attendant les methodes de recuperation de la bdd.
        for (int i = 0; i < 100; i++) {
            editions.add(new Edition(String.valueOf(i), i));
        }
        
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
  
        
        Integer page = 1;
        
        if(request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        request.setAttribute("page", page);
        
        int perPage = 6;
        
        if(request.getParameter("perPage") != null) {
            perPage = Integer.parseInt(request.getParameter("perPage"));
        }
        
        request.setAttribute("perPage", perPage);
        
        List displayed = editions.subList(
                Math.max(page * perPage - perPage, 0), 
                Math.min(page * perPage, editions.size()));
        request.setAttribute("editions", displayed);
        
        int nbPage = (int)Math.ceil((double) editions.size() / perPage);
        request.setAttribute("nbPage", nbPage);
        
        System.out.println("viewing " + editions.size() + " editions");
        System.out.println("from : " + Math.max(page * perPage - perPage, 0));
        System.out.println("to : " + Math.min(page * perPage, editions.size()));
        System.out.println("nbPage : " + nbPage);
        
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
