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
import model.classes.Tag;

public class TagBean {
    
    private static final Logger LOG = Logger.getLogger(TagBean.class.getName());
    

    private static final String SQL_FIND_ALL = "SELECT idTag, libelle FROM Tag ";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idTag, libelle"
            + " FROM Tag"
            + " WHERE idTag = ?";

    private static final String SQL_FIND_BY_OUVRAGE = "SELECT"
            + " t.idTag, t.libelle"
            + " FROM Tag AS t"
            + " JOIN Referencement AS r on r.idTag = t.idTag"
            + " WHERE r.idOuvrage = ?";
    
    
    public List<Tag> findAll(ConnexionBean bc) {
        List<Tag> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Tag item = new Tag();
                
                item.setId(rs.getLong("idTag"));
                item.setLibelle(rs.getString("libelle"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public Tag findById(ConnexionBean bc, Long id) {
        Tag item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                item = new Tag();
                item.setId(rs.getLong("idTag"));
                item.setLibelle(rs.getString("Libelle"));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
    
    public List<Tag> findByOuvrage(ConnexionBean bc, Long idOuvrage) {
        List<Tag> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_OUVRAGE);
            ps.setLong(1, idOuvrage);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tag item = new Tag();
                
                item.setId(rs.getLong("idTag"));
                item.setLibelle(rs.getString("libelle"));
                
                list.add(item);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
}
