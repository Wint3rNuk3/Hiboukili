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
import model.classes.StatutAdresse;

public class StatutAdresseBean {
    
    private static final Logger LOG = Logger.getLogger(StatutAdresseBean.class.getName());
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idStatutAdresse, libelle, code"
            + " FROM StatutAdresse";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idStatutAdresse, libelle, code"
            + " FROM StatutAdresse"
            + " WHERE idStatutAdresse = ?";
    
    public List<StatutAdresse> findAll(ConnexionBean bc) {
        List<StatutAdresse> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                StatutAdresse item = new StatutAdresse();
                
                item.setId(rs.getLong("idStatutAdresse"));
                item.setLibelle(rs.getString("libelle"));
                item.setCode(rs.getString("code"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public StatutAdresse findById(ConnexionBean bc, Long id) {
        StatutAdresse item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                item = new StatutAdresse();
                item.setId(rs.getLong("idStatutAdresse"));
                item.setLibelle(rs.getString("Libelle"));
                item.setCode(rs.getString("code"));
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
}
