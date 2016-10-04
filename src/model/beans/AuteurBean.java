package model.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.classes.Auteur;

public class AuteurBean {
    
    private static final Logger LOG = Logger.getLogger(AuteurBean.class.getName());

    private static final String SQL_FIND_ALL = "SELECT"
            + " idAuteur, nom, prenom, date_naissance, date_deces"
            + " FROM Auteur ";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idAuteur, nom, prenom, date_naissance, date_deces"
            + " FROM Auteur"
            + " WHERE idAuteur = ?";

    private static final String SQL_FIND_CO_AUTEURS_BY_OUVRAGE = "SELECT"
            + " a.idAuteur, a.nom, a.prenom, a.date_naissance, a.date_deces"
            + " FROM Auteur AS a"
            + " JOIN CoAuteur AS co ON a.idAuteur = co.idAuteur"
            + " WHERE co.idOuvrage = ?";
    
    
    public List<Auteur> findAll(BeanConnexion bc) {
        List<Auteur> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Auteur item = new Auteur();
                
                item.setId(rs.getLong("idAuteur"));
                item.setNom(rs.getString("nom"));
                item.setPrenom(rs.getString("prenom"));
                item.setDateNaissance(rs.getDate("date_naissance"));
                item.setDateDeces(rs.getDate("date_deces"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public Auteur findById(BeanConnexion bc, Long id) {
        Auteur item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            item = new Auteur();
            item.setId(rs.getLong("idAuteur"));
            item.setNom(rs.getString("nom"));
            item.setPrenom(rs.getString("prenom"));
            item.setDateNaissance(rs.getDate("date_naissance"));
            item.setDateDeces(rs.getDate("date_deces"));
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
    
    public List<Auteur> findCoAuteursByOuvrage(BeanConnexion bc, Long idOuvrage) {
        List<Auteur> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_CO_AUTEURS_BY_OUVRAGE);
            ps.setLong(1, idOuvrage);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Auteur item = new Auteur();
                
                item.setId(rs.getLong("idAuteur"));
                item.setNom(rs.getString("nom"));
                item.setPrenom(rs.getString("prenom"));
                item.setDateNaissance(rs.getDate("date_naissance"));
                item.setDateDeces(rs.getDate("date_deces"));
                
                list.add(item);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
}
