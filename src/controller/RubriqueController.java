package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.beans.ConnexionBean;
import model.beans.EditionBean;
import model.beans.RubriqueBean;
import model.classes.Edition;
import model.classes.Rubrique;

@WebServlet(name = "RubriqueController", urlPatterns = {"/rubrique"})
public class RubriqueController extends HttpServlet {

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
        
        HttpSession session = request.getSession();           
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        if (bc == null) {
            bc = new ConnexionBean();
            session.setAttribute("sessionConnexion", bc);
        }
        
        Rubrique rubrique = null;
        
        if(request.getParameter("rubrique") != null) {
            Long id = Long.parseLong(request.getParameter("rubrique"));
            rubrique = new RubriqueBean().findById(bc, id);
        }
        
        if(rubrique == null) {
            // send error
            // redirect to referer
            request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
        }
        
        request.setAttribute("rubrique", rubrique);
        
        // on va chercher la liste des editions rattaché a cette rubrique.
        List<Edition> editions = new EditionBean().findByRubrique(bc, rubrique.getId());
        request.setAttribute("editions", editions);
        
//        request.getDispatcherType() == DispatcherType.INCLUDE
//        request.setAttribute("type", request.getDispatcherType());
        request.getRequestDispatcher("/WEB-INF/jsp/rubrique/view.jsp").forward(request, response);
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
