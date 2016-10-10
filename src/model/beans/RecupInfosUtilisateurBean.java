package model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import model.classes.Adresse;
import model.classes.Pays;
import model.classes.Utilisateur;

public class RecupInfosUtilisateurBean implements Serializable {

    public RecupInfosUtilisateurBean() {

    }

    public Utilisateur recupInfosUtilisateur(DataSource ds, ConnexionBean cB, Long idUtilisateur) {
        ds = cB.MaConnexion();
        try (Connection c = ds.getConnection();) {

            String query = "select nom, prenom, date_naissance, telephone from Utilisateur where idUtilisateur = ? ";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setLong(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setDateNaissance(rs.getDate("date_naissance"));
                u.setTelephone(rs.getString("telephone"));
                return u;
            }

        } catch (SQLException ex) {
            System.err.println("Erreur RecupInfosUtilisateurBean 1 - " + ex.getMessage());
        }

        return null;
    }

    public Adresse recupInfosAdresse(DataSource ds, ConnexionBean cB, Long idUtilisateur) {

        ds = cB.MaConnexion();
                System.out.println("sync dans Bean 1 : "+idUtilisateur);//-------------------------------

        try (Connection c = ds.getConnection()) {
                    System.out.println("sync dans Bean 2 : "+idUtilisateur);//-------------------------------

            String query = "select a.idAdresse, numero, voie, codePostal, ville, complement, p.idPays, libelle from DernieresFacturations as df "
                    
                    + "join Adresse as a "
                    + "on df.idAdresse = a.idAdresse "
                    + "join Utilisateur as u "
                    + "on df.idUtilisateur = u.idUtilisateur "
                    + "join pays as p "
                    + "on p.idPays = a.idPays "
                    + "where u.idUtilisateur = ?";

            PreparedStatement ps = c.prepareStatement(query);
            ps.setLong(1, idUtilisateur);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Adresse a = new Adresse();
                a.setId(rs.getLong("idAdresse"));
                a.setNumero(rs.getString("numero"));
                a.setVoie(rs.getString("voie"));
                a.setCp(rs.getString("codePostal"));
                a.setVille(rs.getString("ville"));
                a.setComplement(rs.getString("complement"));
                Pays p = new Pays();
                p.setId(rs.getLong("idPays"));
                p.setLibelle(rs.getString("libelle"));
                a.setPays(p);

                return a;
            }

        } catch (SQLException ex) {
            System.err.println("Erreur RecupInfosUtilisateurBean 2 - " + ex.getMessage());
        }
        return null;
    }
    
    
}
