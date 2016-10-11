package model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;
import model.classes.Adresse;
import model.classes.Pays;
import model.classes.Utilisateur;

public class RecupInfosUtilisateurBean implements Serializable {
    ArrayList<Adresse>myAl;
    public RecupInfosUtilisateurBean() {
        myAl = new ArrayList<>();
    }

    public Utilisateur recupInfosUtilisateur(DataSource ds, ConnexionBean cB, Long idUtilisateur) {
        ds = cB.MaConnexion();
        try (Connection c = ds.getConnection();) {
            //recupere toutes les infos d'un utilisateur
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

//    public Adresse recupInfosAdresse(DataSource ds, ConnexionBean cB, Long idUtilisateur) {
    public Adresse recupInfosAdresse(DataSource ds, ConnexionBean cB, Long idAdresse) {

        ds = cB.MaConnexion();
        try (Connection c = ds.getConnection()) {

//            String query = "select a.idAdresse, numero, voie, codePostal, ville, complement, p.idPays, libelle "
//                    + " from DernieresFacturations as df "                    
//                    + " join Adresse as a "
//                    + " on df.idAdresse = a.idAdresse "
//                    + " join Utilisateur as u "
//                    + " on df.idUtilisateur = u.idUtilisateur "
//                    + " join pays as p "
//                    + " on p.idPays = a.idPays "
//                    + " where u.idUtilisateur = ?";
            //recupere toutes les infos d'une adresse
            String query = "select numero, voie, codePostal, ville, complement, p.idPays, libelle from Adresse "
                    + " as a join pays as p "
                    + " on p.idPays = a.idPays "
                    + " where a.idAdresse = ?";

            PreparedStatement ps = c.prepareStatement(query);
            //ps.setLong(1, idUtilisateur);
            ps.setLong(1, idAdresse);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Adresse a = new Adresse();
                //a.setId(rs.getLong("idAdresse"));
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

    public ArrayList recupListeAdresses(DataSource ds, ConnexionBean cB, Long idUtilisateur) {
        
        ds = cB.MaConnexion();
        try (Connection c = ds.getConnection()) {
            //recupere la liste de toutes les adresses d'un utilisateur
            String query = "select  a.idAdresse, idStatutAdresse, numero, voie, codePostal, ville, complement, libelle from DernieresFacturations as ds "
                    + " join Adresse as a on ds.idAdresse = a.idAdresse "
                    + " join Pays as p "
                    + " on a.idPays = p.idPays "
                    + " where idUtilisateur = ?";
            PreparedStatement ps = c.prepareStatement(query);
            ps.setLong(1, idUtilisateur);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Adresse a = new Adresse();
                Pays p = new Pays();
                a.setId(rs.getLong("idAdresse"));
                a.setStatutAdresse(rs.getLong("idStatutAdresse"));//--------------        
                a.setNumero(rs.getString("numero"));                
                a.setVoie(rs.getString("voie"));
                a.setCp(rs.getString("codePostal"));
                a.setVille(rs.getString("ville"));
                a.setComplement(rs.getString("complement"));
                p.setLibelle(rs.getString("libelle"));
                a.setPays(p);

                myAl.add(a);
            }
        } catch (SQLException exe) {
            System.err.println("Erreur RecupInfosUtilisateurBean 3 - " + exe.getMessage());
        }
        return myAl;
    }
}
