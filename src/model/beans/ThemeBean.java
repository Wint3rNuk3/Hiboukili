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
import model.classes.Theme;

public class ThemeBean {
    
    private static final Logger LOG = Logger.getLogger(ThemeBean.class.getName());

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idTheme, libelle"
            + " FROM Theme"
            + " WHERE idTheme = ?";
    private static final String SQL_FIND_ALL = "SELECT"
            + " idTheme, libelle"
            + " FROM Theme";

    private static final String SQL_FIND_BY_OUVRAGE = "SELECT"
            + " t.idTheme, t.libelle"
            + " FROM Theme AS t"
            + " JOIN Thematique AS th on t.idTheme = th.idTheme"
            + " WHERE th.idOuvrage = ?";

    
    public List<Theme> findAll(ConnexionBean bc) {
        List<Theme> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Theme item = new Theme();
                
                item.setId(rs.getLong("idTag"));
                item.setLibelle(rs.getString("libelle"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public Theme findById(ConnexionBean bc, Long id) {
        Theme item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                item = new Theme();
                item.setId(rs.getLong("idTheme"));
                item.setLibelle(rs.getString("Libelle"));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
    
    public List<Theme> findByOuvrage(ConnexionBean bc, Long idOuvrage) {
        List<Theme> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_OUVRAGE);
            ps.setLong(1, idOuvrage);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Theme item = new Theme();
                
                item.setId(rs.getLong("idTheme"));
                item.setLibelle(rs.getString("libelle"));
                
                list.add(item);
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
}
