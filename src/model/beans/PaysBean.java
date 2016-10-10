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
import model.classes.Pays;

public class PaysBean {
    private static final Logger LOG = Logger.getLogger(PaysBean.class.getName());
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idPays, libelle, code"
            + " FROM Pays";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idPays, libelle, code"
            + " FROM Pays"
            + " WHERE idPays = ?";
    
    public List<Pays> findAll(ConnexionBean bc) {
        List<Pays> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            list = list(rs);
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public Pays findById(ConnexionBean bc, Long id) {
        Pays item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            
            item = one(rs);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item;
    }
    
    protected Pays map(ResultSet resultSet) throws SQLException {
        
        Pays pays = new Pays(); 
        
        pays.setId(resultSet.getLong("idPays"));
        pays.setLibelle(resultSet.getString("libelle"));
        pays.setCode(resultSet.getString("code"));
        
        return pays;
    }
    
    private List<Pays> list(ResultSet rs) throws SQLException {
        List<Pays> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs));
        }
        
        return list;
    }

    private Pays one(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return map(rs);
        }
        
        return null;
    }
}
