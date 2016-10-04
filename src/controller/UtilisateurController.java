
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import model.beans.BeanConnexion;
import model.beans.BeanLogin;

@WebServlet(name = "UtilisateurController", urlPatterns = {"/UtilisateurController"})
public class UtilisateurController extends HttpServlet {
       
            //recupere les cookies et verifie s'ils existent
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

        HttpSession session = request.getSession();
        String url = "/WEB-INF/jsp/pageLogin.jsp";

        //initialisation des cookies
        Cookie cookieLoginReussi = getMyCookies(request.getCookies(), "cookieLoginReussi");
        Cookie cookieLoginRate = getMyCookies(request.getCookies(), "cookieLoginRate");
        //initialisation des requetes
        request.setAttribute("msgLogin", "");//message d'erreur rouge apparaissant au bas du formulaire lorsque login ou mdp faux
        //request.setAttribute("recupLogin", "");//recupere le login saisi ds le champ Login

        //renvoie a la page d'erreur si le login/mdp est errone plus de 3 fois
        if (cookieLoginRate != null && cookieLoginRate.getValue().length() > 2) {
            url = "/WEB-INF/jsp/error.jsp";
        }

        //renvoie a la page de bienvenue lorsque le cookie de Login reussi existe
        if (cookieLoginReussi != null) {
            url = "/WEB-INF/jsp/bienvenue.jsp";
        }

        //lorsqu'on appuie sur le bouton valider du formulaire d'identification
        if (request.getParameter("validerBT") != null) {
            
            //verifie si un beanLogin est enregistre ds la session; si non, le cree
            BeanLogin bl = (BeanLogin) session.getAttribute("sessionLogin");
            if (bl == null) {
                bl = new BeanLogin();
                session.setAttribute("sessionLogin", bl);
            }

            //verifie si un beanConnexion est enregistre ds la session; si non, le cree            
            BeanConnexion bc = (BeanConnexion) session.getAttribute("sessionConnexion");
            if (bc == null) {
                bc = new BeanConnexion();
                session.setAttribute("sessionConnexion", bc);
            }

            DataSource ds = bc.MaConnexion(); //prepare la connexion a la BDD a partir du pool de connexion
            
            //renvoie un boolean verifiant si le couple login/mdp a ete saisi correctement
            boolean b = bl.checkLogin(ds, bc,request.getParameter("loginTI"), request.getParameter("mdpTI"));

            //recupere le login saisi ds le champ Login
            request.setAttribute("recupLogin", request.getParameter("loginTI"));

            if (b) {//si le couple login/mdp est exact
                url = "/WEB-INF/jsp/bienvenue.jsp";//renvoie a la page de bienvenue
                //cree un cookie de login reussi
                cookieLoginReussi = new Cookie("cookieLoginReussi", request.getParameter("loginTI"));
                cookieLoginReussi.setMaxAge(60 * 60 * 24);
                response.addCookie(cookieLoginReussi);

                //s'il existe un cookie de login rate, il le supprime
                if (cookieLoginRate != null) {
                    cookieLoginRate.setMaxAge(0);
                    response.addCookie(cookieLoginRate);
                }
            } else {//sinon, si le couple login/mdp est errone
                //cree un cookie de login rate s'il n'existe pas encore
                if (cookieLoginRate == null) {
                    cookieLoginRate = new Cookie("cookieLoginRate", "*");
                } else {//si le cookie de login rate existe deja
                    String s = cookieLoginRate.getValue();//recupere sa valeur
                    s += "*";// ajoute 1 tentative de login erronee au cookie
                    cookieLoginRate.setValue(s);
                }
                cookieLoginRate.setMaxAge(30);
                response.addCookie(cookieLoginRate);

            }
            //met a jour le message d'erreur qui s'affiche lorsque le login/mdp est faux
            request.setAttribute("msgLogin", "Erreur : Login/Mot de passe invalide(s) !!!");
        }

        //lorsqu'on appuie sur le bouton deconnecter de la page de bienvenue
        if (request.getParameter("deconnecterBT") != null) {
            cookieLoginReussi.setMaxAge(0);//supprime le cookie de login reussi
            response.addCookie(cookieLoginReussi);
            url = "/WEB-INF/jsp/pageLogin.jsp";//renvoie a la page du formulaire de login

        }

        request.getRequestDispatcher(url).include(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
