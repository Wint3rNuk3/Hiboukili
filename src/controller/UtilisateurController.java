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
import model.beans.ConnexionBean;
import model.beans.ControleSaisieCreationCompteBean;
import model.beans.ListePaysBean;
import model.beans.LoginBean;
import model.beans.UpdateBDDBean;
import model.classes.Utilisateur;

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

        DataSource ds = null;
        String[] messageErreur = null;
        int i1 = 0;//id Utilisateur
        int j2 = 0;//id Adresse
        ConnexionBean bc = (ConnexionBean) session.getAttribute("sessionConnexion");
        ListePaysBean blp = (ListePaysBean) session.getAttribute("sessionBeanListePays");
        ControleSaisieCreationCompteBean bcscc
                = (ControleSaisieCreationCompteBean) request.getAttribute("BeanControleSaisieCreationCompte");
        UpdateBDDBean buBdd = (UpdateBDDBean) session.getAttribute("BeanUpdateBDD");

/////////       
        //cree une session de connexion si non existante        
        if (bc == null) {
            bc = new ConnexionBean();
        }

        //cree une session pour generer la liste des pays ds le combobox si non existante
        if (blp == null) {
            blp = new ListePaysBean();
        }

        //cree une session pour le controle des saisies si non existante
        if (bcscc == null) {
            bcscc = new ControleSaisieCreationCompteBean();
        }

///////
        String url = "/WEB-INF/jsp/creationCompte.jsp";//CREATION DE COMPTE
        //String url = "/WEB-INF/pageLogin.jsp";//LOGIN

        //configure la requete pour la gestion du message d'erreur en bas du formulaire de creation de compte 
        request.setAttribute("erreurSaisie", "ok");

        //lorsqu'on appuie sur le bouton creer un compte du formulaire de creation de compte
        if (request.getParameter("creerCompteBT") != null) {

            //enregistre toutes les saisies ds une variable et ds une requete
            String saisieNom = request.getParameter("nomTI");
            request.setAttribute("recupNomCompte", request.getParameter("nomTI"));

            String saisiePrenom = request.getParameter("prenomTI");
            request.setAttribute("recupPrenomCompte", request.getParameter("prenomTI"));

            String saisieDob = request.getParameter("dobTI");
            request.setAttribute("recupDobCompte", request.getParameter("dobTI"));

            String saisieTel = request.getParameter("telTI");
            request.setAttribute("recupTelCompte", request.getParameter("telTI"));

            String saisieMail = request.getParameter("mailTI");
            request.setAttribute("recupMailCompte", request.getParameter("mailTI"));

            String saisieMdp = request.getParameter("mdpTI");
            request.setAttribute("recupMdpCompte", request.getParameter("mdpTI"));

            String saisieConfMdp = request.getParameter("confMdpTI");
            request.setAttribute("recupConfMdpCompte", request.getParameter("confMdpTI"));

            //a voir si on garde cette partie du code, gestion des champs nuls 
            //se fait pas par un bean mais ds les balises HTML directement (required)
            messageErreur = bcscc.checkInfo(saisieNom, saisiePrenom, saisieDob, saisieTel,
                    saisieMail, saisieMdp, saisieConfMdp);
            //pour l'instant, verifie seulement que le mot de passe est identique ds les 2 champs
            if (messageErreur != null) {
                request.setAttribute("erreurSaisie", messageErreur[0]);

            } else {

                //prepare la connexion au pool de connexion
                if (bc == null) {
                    bc = new ConnexionBean();
                    session.setAttribute("sessionConnexion", bc);
                }

                //prepare l'enregistrement des infos ds la bdd
                if (buBdd == null) {
                    buBdd = new UpdateBDDBean();
                }

                ds = bc.MaConnexion(); //prepare la connexion a la BDD a partir du pool de connexion

                //envoie les saisies pour qu'elles soient enregistrees ds la bdd et
                //recupere l'idUtilisateur renvoye par la bdd ds i1
                

                i1 = buBdd.creeCompteDsBdd(ds, bc, request.getParameter("nomTI"),
                        request.getParameter("prenomTI"),
                        request.getParameter("dobTI"),
                        request.getParameter("telTI"),
                        request.getParameter("mailTI"),
                        request.getParameter("mdpTI"));
                url = "/WEB-INF/jsp/infosAdresse.jsp";

                            System.out.println("test 1");
                
                
                session.setAttribute("recupInt1", i1);//enregistre l'id utilisateur ds une requete
            }
        }
////////////////////////////////////////////////////////////////////////////////
        //recupere la liste des pays et l'enregistre ds la requete liste
        blp.getListFromBdd(ds, bc);
        request.setAttribute("liste", blp.returnMapValues());

        //lorsqu'on appuie sur le bouton valider adresse du formulaire d'adresse
        if (request.getParameter("validerAdresseBT") != null) {

            //recupere la "value"  <OPTION value = "${i.idPays}" >"${i.libelle}"</OPTION> 
            //donc l'idPays et nom pas le libelle
            int idPays = Integer.valueOf(request.getParameter("paysSL"));

            int idStatutAdresse = 1;
            request.setAttribute("idStatutAdresse", 1);//toujours active a la creation de compte

            //enregistre toutes les saisies ds une variable et ds une requete
            String saisieNumAdresse = request.getParameter("numAdresseTI");
            request.setAttribute("recupNumAdresse", request.getParameter("numAdresseTI"));

            String saisieRueAdresse = request.getParameter("rueTI");
            request.setAttribute("recupRueAdresse", request.getParameter("rueTI"));

            String saisieCpAdresse = request.getParameter("cpTI");
            request.setAttribute("recupcpAdresse", request.getParameter("cpTI"));

            String saisieVilleAdresse = request.getParameter("villeTI");
            request.setAttribute("recupVilleAdresse", request.getParameter("villeTI"));

            String saisieInfosCompAdresse = request.getParameter("infosCompTI");
            request.setAttribute("recupInfosCompAdresse", request.getParameter("infosCompTI"));

            messageErreur = bcscc.checkInfoAdresse();//Controles a implementer plus tard

            if (messageErreur != null) {
                request.setAttribute("erreurSaisie", messageErreur[0]);
                //section vide pour l'instant
            } else {

                //enregistrement des infos ds la bdd
            }

            //prepare la connexion au pool de connexion
            if (bc == null) {
                bc = new ConnexionBean();
                session.setAttribute("sessionConnexion", bc);
            }

            //prepare l'enregistrement des infos ds la bdd
            buBdd = (UpdateBDDBean) session.getAttribute("BeanUpdateBDD");
            if (buBdd == null) {
                buBdd = new UpdateBDDBean();

                ds = bc.MaConnexion(); //prepare la connexion a la BDD a partir du pool de connexion

                //envoie les saisies pour qu'elles soient enregistrees ds la bdd et
                //recupere l'idAdresse renvoye par la bdd ds j2                
                j2 = buBdd.creeAdresse(ds, bc, idPays, saisieNumAdresse,
                        saisieRueAdresse,
                        saisieCpAdresse,
                        saisieVilleAdresse,
                        saisieInfosCompAdresse);

                i1 = (int) session.getAttribute("recupInt1");//recupere l'id Utilisateur
                //envoie les id utilisateur et adresse pour l'enregistrement ds la table dernieresFacturations
                buBdd.updateDernieresFacturations(ds, bc, i1, j2);

                url = "/WEB-INF/jsp/bienvenue.jsp";
            }

        }

////////////////////////////////////////////////////////////////////////////////
//                                      LOGIN
////////////////////////////////////////////////////////////////////////////////           
        //String url = "/WEB-INF/jsp/pageLogin.jsp";
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

        //gestion de la section Login
        if ("sectionLogin".equals(request.getParameter("section"))) {
            //lorsqu'on appuie sur le bouton valider du formulaire d'identification
            if (request.getParameter("validerBT") != null) {

                //verifie si un beanLogin est enregistre ds la session; si non, le cree
                LoginBean bl = (LoginBean) session.getAttribute("sessionLogin");
                if (bl == null) {
                    bl = new LoginBean();
                    session.setAttribute("sessionLogin", bl);
                }

                //verifie si un beanConnexion est enregistre ds la session; si non, le cree            
                bc = (ConnexionBean) session.getAttribute("sessionConnexion");
                if (bc == null) {
                    bc = new ConnexionBean();
                    session.setAttribute("sessionConnexion", bc);
                }

                ds = bc.MaConnexion(); //prepare la connexion a la BDD a partir du pool de connexion

                //renvoie un objet Utilisateur si le couple login/mdp a ete saisi correctement
                Utilisateur uti = bl.checkLogin(ds, bc, request.getParameter("loginTI"), request.getParameter("mdpTI"));

                //recupere le login saisi ds le champ Login
                request.setAttribute("recupLogin", request.getParameter("loginTI"));

                //si l'objet utilisateur existe (donc login/mdp saisis correctement) mais que le statut est desactive
                if (uti != null && uti.getStatut().getCode().trim().equals("NOK")) {
                    url = "/WEB-INF/jsp/compteDesactive.jsp";//renvoie a la page desactive
                } else if (uti != null && uti.getStatut().getCode().trim().equals("OK")) {//si le couple login/mdp est exact avec un statut valide

                    url = "/WEB-INF/jsp/bienvenue.jsp";//renvoie a la page de bienvenue

                    //enregistre l'objet ds la session
                    session.setAttribute("utilisateur", uti);
                    //enregistre le prenom ds une requete pour l'utiliser ds la page bienvenue
                    request.setAttribute("prenomUtilisateur", uti.getPrenom());
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
        }

        //gestion du bouton deconnecter page de bienvenue
        if (request.getParameter("deconnecterBT") != null) {
            cookieLoginReussi.setMaxAge(0);//supprime le cookie de login reussi
            response.addCookie(cookieLoginReussi);
            session.setAttribute("utilisateur", null);//supprime l'objet utilisateur de la session

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
