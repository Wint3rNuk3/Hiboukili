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
import model.classes.Genre;

public class GenreBean {
    
    private static final Logger LOG = Logger.getLogger(GenreBean.class.getName());
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idGenre, libelle"
            + " FROM Genre ";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " g.idGenre, g.libelle"
            + " FROM Genre as g"
            + " WHERE g.idGenre = ?";
    
    private static final String SQL_FIND_BY_OUVRAGE = "SELECT"
            + " g.idGenre, g.libelle"
            + " FROM Genre as g"
            + " JOIN IndexGenre AS i on i.idGenre = g.idGenre"
            + " WHERE i.idOuvrage = ?";
    
    public List<Genre> findAll(ConnexionBean bc) {
        List<Genre> genres = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Genre genre = new Genre();
                Long id = rs.getLong("idGenre");
                
                genre.setId(id);
                genre.setLibelle(rs.getString("Libelle"));
       
                genres.add(genre);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return genres;
    }
    
    public Genre findById(ConnexionBean bc, Long id) {
        Genre genre = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            
                genre = new Genre();
                genre.setId(rs.getLong("idGenre"));
                genre.setLibelle(rs.getString("Libelle"));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return genre; 
    }
    
    public List<Genre> findByOuvrage(ConnexionBean bc, Long idOuvrage) {
        List<Genre> genres = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_OUVRAGE);
            ps.setLong(1, idOuvrage);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Genre genre = new Genre();
                
                genre.setId(rs.getLong("idGenre"));
                genre.setLibelle(rs.getString("Libelle"));

                genres.add(genre);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return genres;
    }
    
}
