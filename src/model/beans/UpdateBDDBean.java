
package model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class UpdateBDDBean implements Serializable {

    public UpdateBDDBean() {

    }

    public int creeCompteDsBdd(DataSource ds,
            ConnexionBean bc, String nom, String prenom, String date, String tel, String mail, String mdp) {

        int i = 0;

        ds = bc.MaConnexion();
        //enregistre les donnees utilisateur ds la bdd
        try (Connection c = ds.getConnection()) {
            String update = "INSERT INTO Utilisateur ( idStatutUtilisateur, nom, prenom, date_naissance, telephone, mail,  mot_de_passe) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(update);

            ps.setInt(1, 1);
            ps.setString(2, nom);
            ps.setString(3, prenom);
            Date d = Date.valueOf(date);
            ps.setDate(4, d);
            ps.setString(5, tel);

            //ps.setDate(4, getCurrentDate());
            ps.setString(6, mail);
            ps.setString(7, mdp);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erreur dans BeanUpdateBDD 1 - " + ex.getMessage());
        }

        ds = bc.MaConnexion();
        
        //recupere l'id utilisateur
        try (Connection c = ds.getConnection()) {
            String query = "select max(idUtilisateur) from Utilisateur";//dernier idUtilisateur enregistre
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                i = rs.getInt(1);
            }

        } catch (SQLException exp) {
            System.err.println("Erreur dans BeanUpdateBDD 2 - " + exp.getMessage());
        }
        return i;//renvoie l'id utilisateur
    }

////////////////////////////////////////////////////////////////////////////////
    public int creeAdresse(DataSource ds,
            ConnexionBean bc, int idPays, String numero, String voie, String codePostal, String ville, String complement) {

        int j = 0;
        ds = bc.MaConnexion();
        //enregistre les donnees adresse ds la bdd        
        try (Connection c = ds.getConnection()) {            
            String update = "INSERT INTO Adresse(idPays, idStatutAdresse,  numero, voie, codePostal, ville, complement) "
                    + "VALUES ( ? , ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = c.prepareStatement(update);

            ps.setInt(1, idPays);            
            ps.setInt(2, 1);
            ps.setString(3, numero);
            ps.setString(4, voie);
            ps.setString(5, codePostal);
            ps.setString(6, ville);
            ps.setString(7, complement);

            ps.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erreur dans BeanUpdateBDD 3 - " + ex.getMessage());
        }

        ds = bc.MaConnexion();
        //recupere l'id adresse        
        try (Connection c = ds.getConnection()) {
            String query = "select max(idAdresse) from Adresse";//dernier idAdresse enregistre
            PreparedStatement ps = c.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                j = rs.getInt(1);
            }

        } catch (SQLException exp) {
            System.err.println("Erreur dans BeanUpdateBDD 4 - " + exp.getMessage());
        }
//--------------------------

        return j;//renvoie l'id adresse
    }

    public void updateDernieresFacturations(DataSource ds, ConnexionBean bc, int i1, int j2) {
        ds = bc.MaConnexion();
        //enregistre les id utilisateur et adresse ds la table dernieresFacturations        
        try (Connection c = ds.getConnection()) {
            String update = "insert into DernieresFacturations (idUtilisateur, idAdresse) values (?, ?)";
            PreparedStatement ps = c.prepareStatement(update);

            ps.setInt(1, i1);
            ps.setInt(2, j2);

            ps.executeUpdate();

        } catch (SQLException exp) {
            System.err.println("Erreur dans BeanUpdateBDD 4 - " + exp.getMessage());
        }
    }
}
