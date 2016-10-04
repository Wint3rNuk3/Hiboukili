
package model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class BeanLogin implements Serializable {

    public BeanLogin() {

    }
//    public boolean checkLogin(String login, String mdp){
//        
//        if(login.equals("admin") && mdp.equals("root")){
//            return true;
//        }
//        return false;
//    }

    public boolean checkLogin(DataSource ds, BeanConnexion bc, String login, String mdp) {

        ds = bc.MaConnexion();//connexion
        try (Connection c = ds.getConnection()) {
            //requete
            String query = "select mail, mot_de_passe from Utilisateur where mail = ? and mot_de_passe = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, login);//renvoie la valeur du login saisi pour where mail = ?
            ps.setString(2, mdp);//renvoie la valeur du mdp saisi pour where mot_de_passe = ?

            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){//s'il existe un couple login/mdp existant ds la BDD
                return true; //renvoie vrai
            }

        } catch (SQLException ex) {
            System.err.println("Erreur dans BeanLogin : " + ex.getMessage() + "\n");
        }

        return false;//sinon renvoie faux
    }
}
