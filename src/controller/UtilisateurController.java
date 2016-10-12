package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import model.beans.RecupInfosUtilisateurBean;
import model.beans.UpdateBDDBean;
import model.classes.Adresse;
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
        String url = null;
        int i1 = 0;//id Utilisateur
        int j2 = 0;//id Adresse
        ArrayList<Adresse> myAL = null;//ArrayList enregistrant toutes les adresses d'un utilisateur

        ConnexionBean cB = (ConnexionBean) session.getAttribute("sessionConnexion");
        ListePaysBean blp = (ListePaysBean) session.getAttribute("sessionBeanListePays");
        ControleSaisieCreationCompteBean bcscc
                = (ControleSaisieCreationCompteBean) request.getAttribute("BeanControleSaisieCreationCompte");
        UpdateBDDBean uBddB = (UpdateBDDBean) session.getAttribute("BeanUpdateBDD");
        RecupInfosUtilisateurBean riub = (RecupInfosUtilisateurBean) session.getAttribute("RecupInfosUtilisateurBean()");

        Cookie cookieLoginReussi = getMyCookies(request.getCookies(), "cookieLoginReussi");
        Cookie cookieLoginRate = getMyCookies(request.getCookies(), "cookieLoginRate");
        Cookie sync = getMyCookies(request.getCookies(), "sync");
/////////       
        //cree une session de connexion si non existante        
        if (cB == null) {
            cB = new ConnexionBean();
        }

        //cree une session pour generer la liste des pays ds le combobox si non existante
        if (blp == null) {
            blp = new ListePaysBean();
        }

        //cree une session pour le controle des saisies si non existante
        if (bcscc == null) {
            bcscc = new ControleSaisieCreationCompteBean();
        }

////////////////////////////////////////////////////////////////////////////////
//                        CREATION COMPTE - SECTION UTILISATEUR
////////////////////////////////////////////////////////////////////////////////
        url = "/WEB-INF/jsp/pageLogin.jsp";//LOGIN

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
                if (cB == null) {
                    cB = new ConnexionBean();
                    session.setAttribute("sessionConnexion", cB);
                }

                //prepare l'enregistrement des infos ds la bdd
                if (uBddB == null) {
                    uBddB = new UpdateBDDBean();
                }

//                ds = cB.MaConnexion(); //prepare la connexion a la BDD a partir du pool de connexion
                //envoie les saisies pour qu'elles soient enregistrees ds la bdd et
                //recupere l'idUtilisateur renvoye par la bdd ds i1
                i1 = uBddB.creeCompteDsBdd(ds, cB,
                        request.getParameter("nomTI"),
                        request.getParameter("prenomTI"),
                        request.getParameter("dobTI"),
                        request.getParameter("telTI"),
                        request.getParameter("mailTI"),
                        request.getParameter("mdpTI"));
                url = "/WEB-INF/jsp/infosAdresse.jsp";

                session.setAttribute("recupInt1", i1);//enregistre l'id utilisateur ds une requete

                if (sync == null) {
                    sync = new Cookie("sync", Integer.toString(i1));
                    sync.setMaxAge(24 * 60 * 60);
                    response.addCookie(sync);
                }

                if (cookieLoginReussi == null) {
                    sync = new Cookie("cookieLoginReussi", request.getParameter("mailTI"));
                    sync.setMaxAge(24 * 60 * 60);
                    response.addCookie(sync);
                }
            }
        }

////////////////////////////////////////////////////////////////////////////////
//                                      LOGIN
////////////////////////////////////////////////////////////////////////////////           
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
                LoginBean lB = (LoginBean) session.getAttribute("sessionLogin");
                if (lB == null) {
                    lB = new LoginBean();
                    session.setAttribute("sessionLogin", lB);
                }

                //verifie si un beanConnexion est enregistre ds la session; si non, le cree            
                cB = (ConnexionBean) session.getAttribute("sessionConnexion");
                if (cB == null) {
                    cB = new ConnexionBean();
                    session.setAttribute("sessionConnexion", cB);
                }

                //renvoie un objet Utilisateur si le couple login/mdp a ete saisi correctement
                Utilisateur uti = lB.checkLogin(ds, cB, request.getParameter("loginTI"), request.getParameter("mdpTI"));

                //recupere le login saisi ds le champ Login
                request.setAttribute("recupLogin", request.getParameter("loginTI"));

                //si l'objet utilisateur existe (donc login/mdp saisis correctement) mais que le statut est desactive
                if (uti != null && uti.getStatut().getCode().trim().equals("NOK")) {
                    url = "/WEB-INF/jsp/compteDesactive.jsp";//renvoie a la page desactive
                } else if (uti != null && uti.getStatut().getCode().trim().equals("OK")) {//si le couple login/mdp est exact avec un statut valide

                    if (sync == null) {

                        sync = new Cookie("sync", uti.getId().toString());
                        sync.setMaxAge(24 * 60 * 60);
                        response.addCookie(sync);
                    }

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
        if ("deconnexion".equals(request.getParameter("section"))) {
            cookieLoginReussi.setMaxAge(0);//supprime le cookie de login reussi    
            response.addCookie(cookieLoginReussi);
            sync.setMaxAge(0);//supprime le cookie de login reussi
            response.addCookie(sync);

            session.setAttribute("utilisateur", null);//supprime l'objet utilisateur de la session

            url = "/WEB-INF/jsp/pageLogin.jsp";//renvoie a la page du formulaire de login

        }

////////////////////////////////////////////////////////////////////////////////
//                        MODIFICATION COMPTE - SECTION UTILISATEUR
////////////////////////////////////////////////////////////////////////////////
        if ("modifierInfosPerso".equals(request.getParameter("section"))) {

            if (riub == null) {
                riub = new RecupInfosUtilisateurBean();
            }

            if (cB == null) {
                cB = new ConnexionBean();
            }
            //a partir de l'idUtilisateur (enregistree dans le cookie sync), on recupere les infos utilisateur
            Utilisateur u = riub.recupInfosUtilisateur(ds, cB, Long.valueOf(sync.getValue()));

            //on enregistre les infos utilisateur ds la session qui permettra ensuite la lecture des infos
            //ds la jsp et fera ainsi apparaitre les infos utilisateur directmeent ds les champs
            session.setAttribute("recupNomCompte", u.getNom());
            session.setAttribute("recupPrenomCompte", u.getPrenom());
            session.setAttribute("recupDobCompte", u.getDateNaissance());
            session.setAttribute("recupTelCompte", u.getTelephone());

            //appelle la page de creation de compte
            url = "/WEB-INF/jsp/creationCompte.jsp";
        }

        //si on clique sur le bouton modifier 
        if (request.getParameter("modifCompteBT") != null) {
            //enregistre les infos contenues ds les champs  ds des variables et ds des requetes
            String saisieNom = request.getParameter("nomTI");
            request.setAttribute("recupNomCompte", request.getParameter("nomTI"));

            String saisiePrenom = request.getParameter("prenomTI");
            request.setAttribute("recupPrenomCompte", request.getParameter("prenomTI"));

            String saisieTel = request.getParameter("telTI");
            request.setAttribute("recupTelCompte", request.getParameter("telTI"));

            if (uBddB == null) {
                uBddB = new UpdateBDDBean();
            }

            if (cB == null) {
                cB = new ConnexionBean();
            }
            //met a jour les infos utilisateur ds la BDD
            uBddB.MajInfosUtilisateur(cB, ds, saisieNom, saisiePrenom, saisieTel, Long.valueOf(sync.getValue()));
        }

////////////////////////////////////////////////////////////////////////////////
//                        MODIFICATION COMPTE - SECTION ADRRESSE
//////////////////////////////////////////////////////////////////////////////// 
        if ("modifierAdresse".equals(request.getParameter("section"))) {
            url = "/WEB-INF/jsp/infosAdresse.jsp";

            if (cB == null) {
                cB = new ConnexionBean();
            }

            if (riub == null) {
                riub = new RecupInfosUtilisateurBean();
            }

            if (uBddB == null) {
                uBddB = new UpdateBDDBean();
            }

////////////////////////// MODIF ADRESSE PAR DEFAUT ////////////////////////////   
            //lorsqu'on clique sur "Choisir cette adresse comme adresse de facturation"  
            if (request.getParameter("defaut") != null) {         
                //recupere les adresses utilisateur depuis la BDD
                myAL = riub.recupListeAdresses(ds, cB, Long.valueOf(sync.getValue()));
                for (Adresse a : myAL) {
                    //si l'id de l'adresse ds la bdd correspond a l'id renvoye par le lien sur lequel on a clique
                    if (a.getId() == Long.valueOf(request.getParameter("defaut"))) {
                        a.setStatutAdresse(1);//met a jour l'arraylist (necessaire pour l'affichage)
                        uBddB.defautAdresse(ds, cB, 1, a.getId());//renvoie la valeur 1 ds la bdd
                    } else {
                        a.setStatutAdresse(2);
                        uBddB.defautAdresse(ds, cB, 2, a.getId());
                    }
                }
                // myAL = riub.recupListeAdresses(ds, cB, Long.valueOf(sync.getValue()));
                session.setAttribute("listeAdresses", myAL);

                //url = "/WEB-INF/jsp/bienvenue.jsp";
                url = "/WEB-INF/jsp/listeAdresses.jsp";
            }
////////////////////////// MODIF ADRESSE ///////////////////////////////////////

            //clic sur le lien hypertexte Modifier cette adresse d'une adresse
            if (request.getParameter("modif") != null) {//request.getParameter("modif")=id de l'adresse a modifier ds listeAdresses
                Adresse a = riub.recupInfosAdresse(ds, cB, Long.valueOf(request.getParameter("modif")));

                //recupere les infos Adresse de l'utilisateur depuis la BDD
                session.setAttribute("recupIdPays", a.getPays().getId());
                session.setAttribute("recupIdAdresse", Long.valueOf(request.getParameter("modif")));
                session.setAttribute("recupNumAdresse", a.getNumero());
                session.setAttribute("recupVoieAdresse", a.getVoie());
                session.setAttribute("recupCpAdresse", a.getCp());
                session.setAttribute("recupVilleAdresse", a.getVille());
                session.setAttribute("recupInfosCompAdresse", a.getComplement());
                session.setAttribute("recupPaysAdresse", a.getPays().getLibelle());
            }
        }
        //lorsqu'on clique sur le bouton modifier l'adresse
        if (request.getParameter("modifierAdresseBT") != null && sync.getValue() != null) {
            if (uBddB == null) {
                uBddB = new UpdateBDDBean();
            }
            //recupere les infos depuis les champs de texte et le combobox pays et les envoie ds le bean UpdateBDD pour mettre a jour la BDD
            uBddB.MajInfosAdresse(cB, ds,
                    request.getParameter("numAdresseTI"),
                    request.getParameter("voieTI"),
                    request.getParameter("cpTI"),
                    request.getParameter("villeTI"),
                    request.getParameter("infosCompTI"),
                    Long.valueOf(request.getParameter("paysSL")),
                    //(Long) session.getAttribute("recupIdAdresse"));
                    (Long) (session.getAttribute("recupIdAdresse")));
        }

////////////////////////////////////////////////////////////////////////////////
//                        AJOUT NOUVELLE ADRRESSE
//////////////////////////////////////////////////////////////////////////////// 
        if ("ajouterAdresse".equals(request.getParameter("section"))) {
            //vide les champs de saisie
            session.setAttribute("recupNumAdresse", null);
            session.setAttribute("recupVoieAdresse", null);
            session.setAttribute("recupCpAdresse", null);
            session.setAttribute("recupVilleAdresse", null);
            session.setAttribute("recupInfosCompAdresse", null);

            url = "/WEB-INF/jsp/infosAdresse.jsp";//renvoie a la page de bienvenue    
        }
//------------------------------------------------------------------------------
        //recupere la liste des pays et l'enregistre ds la requete liste
        blp.getListFromBdd(ds, cB);
        request.setAttribute("liste", blp.returnMapValues());

        //lorsqu'on appuie sur le bouton valider adresse du formulaire d'adresse
        if (request.getParameter("validerAdresseBT") != null && sync.getValue() != null) {

            //recupere la "value"  <OPTION value = "${i.idPays}" >"${i.libelle}"</OPTION> 
            //donc l'idPays et nom pas le libelle
            int idPays = Integer.valueOf(request.getParameter("paysSL"));

            int idStatutAdresse = 2;
            request.setAttribute("idStatutAdresse", 2);//toujours DESactive a la creation de compte

            //enregistre toutes les saisies ds une variable et ds une requete
            String saisieNumAdresse = request.getParameter("numAdresseTI");
            request.setAttribute("recupNumAdresse", request.getParameter("numAdresseTI"));

            String saisieVoieAdresse = request.getParameter("voieTI");
            request.setAttribute("recupVoieAdresse", request.getParameter("voieTI"));

            String saisieCpAdresse = request.getParameter("cpTI");
            request.setAttribute("recupCpAdresse", request.getParameter("cpTI"));

            String saisieVilleAdresse = request.getParameter("villeTI");
            request.setAttribute("recupVilleAdresse", request.getParameter("villeTI"));

            String saisieInfosCompAdresse = request.getParameter("infosCompTI");
            request.setAttribute("recupInfosCompAdresse", request.getParameter("infosCompTI"));

            String saisiePaysAdresse = request.getParameter("paysSL");
            request.setAttribute("recupPaysAdresse", request.getParameter("paysSL"));

            messageErreur = bcscc.checkInfoAdresse();//Controles a implementer plus tard

            if (messageErreur != null) {
                request.setAttribute("erreurSaisie", messageErreur[0]);
                //section vide pour l'instant
            } else {

                //enregistrement des infos ds la bdd
            }

            //prepare la connexion au pool de connexion
            if (cB == null) {
                cB = new ConnexionBean();
                session.setAttribute("sessionConnexion", cB);
            }

            //prepare l'enregistrement des infos ds la bdd
            uBddB = (UpdateBDDBean) session.getAttribute("BeanUpdateBDD");
            if (uBddB == null) {
                uBddB = new UpdateBDDBean();

                if (riub == null) {
                    riub = new RecupInfosUtilisateurBean();
                }
                int monStatutAdresse = 0;
                //si l'arraylist contenant les adresses est vide (si c'est la 1ere adresse saisie de l'utilisateur) alors valeur de idStatutAdresse = 1
                myAL = riub.recupListeAdresses(ds, cB, Long.valueOf(sync.getValue()));
                if (myAL.size() == 0) {
                    monStatutAdresse = 1;
                } else {//si arraylist non vide(alors l'utilisateur a deja une premiere adresse enregistree)
                    monStatutAdresse = 2;
                }

                //envoie les saisies pour qu'elles soient enregistrees ds la bdd et
                //recupere l'idAdresse renvoye par la bdd ds j2                
                j2 = uBddB.creeAdresse(ds, cB, idPays, monStatutAdresse, saisieNumAdresse,
                        saisieVoieAdresse,
                        saisieCpAdresse,
                        saisieVilleAdresse,
                        saisieInfosCompAdresse);

                String sss = sync.getValue();
                i1 = Integer.valueOf(sss);//recupere l'id Utilisateur
                //envoie les id utilisateur et adresse pour l'enregistrement ds la table dernieresFacturations
                uBddB.updateDernieresFacturations(ds, cB, i1, j2);

                url = "/WEB-INF/jsp/bienvenue.jsp";
            }
        }

//------------------------------------------------------------------------------            
        if ("versNouveauCompte".equals(request.getParameter("section"))) {
            url = "/WEB-INF/jsp/creationCompte.jsp";//CREATION DE COMPTE            
        }

////////////////////////////////////////////////////////////////////////////////
//                        LISTE DES ADRESSES
////////////////////////////////////////////////////////////////////////////////   
        if ("gererAdresses".equals(request.getParameter("section"))) {
            url = "/WEB-INF/jsp/listeAdresses.jsp";

            if (riub == null) {
                riub = new RecupInfosUtilisateurBean();
            }
            //recupere toutes les adresses de l'utilisateur
            myAL = riub.recupListeAdresses(ds, cB, Long.valueOf(sync.getValue()));

            session.setAttribute("listeAdresses", myAL);
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
