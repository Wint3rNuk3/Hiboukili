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
import model.classes.StatutEdition;

public class StatutEditionBean {
    
    private static final Logger LOG = Logger.getLogger(StatutEditionBean.class.getName());
    
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idStatutEdition, libelle, code"
            + " FROM StatutEdition";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idStatutEdition, libelle, code"
            + " FROM StatutEdition"
            + " WHERE idStatutEdition = ?";
    
    public List<StatutEdition> findAll(BeanConnexion bc) {
        List<StatutEdition> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                StatutEdition item = new StatutEdition();
                
                item.setId(rs.getLong("idStatutEdition"));
                item.setLibelle(rs.getString("libelle"));
                item.setCode(rs.getString("code"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public StatutEdition findById(BeanConnexion bc, Long id) {
        StatutEdition item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                item = new StatutEdition();
                item.setId(rs.getLong("idStatutEdition"));
                item.setLibelle(rs.getString("Libelle"));
                item.setCode(rs.getString("code"));
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
}
