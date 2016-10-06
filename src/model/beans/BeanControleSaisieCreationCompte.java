
package model.beans;

import java.io.Serializable;

public class BeanControleSaisieCreationCompte implements Serializable {

    public BeanControleSaisieCreationCompte() {

    }

    public String[] checkInfo(String saisieNom, String saisiePrenom, String saisieDob, String saisieMail,
            String saisieTel, String saisieMdp, String saisieConfMdp) {
        String tab[] = new String[2];
        
        //verification que les champs ne sont pas vides
//        if (saisieNom.trim().isEmpty()) {
//            tab[0] = "Erreur, le champ Nom ne peut être vide !";
//            tab[1] = "nom";
//
//            return tab;
//        }
//        if (saisiePrenom.trim().isEmpty()) {
//            tab[0] = "Erreur, le champ Prenom ne peut être vide !";
//            tab[1] = "prenom";
//
//            return tab;
//        }
//
//        if (saisieMail.trim().isEmpty()) {
//            tab[0] = "Erreur, le champ Mail ne peut être vide !";
//            tab[1] = "mail";
//
//            return tab;
//        }    
//
//        if (saisieMdp.trim().isEmpty()) {
//            tab[0] = "Erreur, le champ Mot de passe ne peut être vide !";
//            tab[1] = "mdp";
//
//            return tab;
//        } 
//        
//        if (saisieConfMdp.trim().isEmpty()) {
//            tab[0] = "Erreur, le champ Confirmation du mot de passe ne peut être vide !";
//            tab[1] = "confMdp";
//
//            return tab;
//        }      

        //verification que le mot de passe et la confirmation sont les memes
        if (!saisieMdp.equals(saisieConfMdp)) {
            tab[0] = "Erreur, les champs Mot de passe et "
                    + "Confirmation du mot de passe ne peuvent être différents !";
            tab[1] = "mdp";

            return tab;
        }           
             
        return null;
    }   
    
        public String[] checkInfoAdresse () {
        String tab[] = new String[2];

        return null;
    }
}
