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
import model.classes.Taxe;

public class TaxeBean {
    
    private static final Logger LOG = Logger.getLogger(TaxeBean.class.getName());

    private static final String SQL_FIND_ALL = "SELECT"
            + " idTaxe, libelle, valeur"
            + " FROM Taxe";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idTaxe, libelle, valeur"
            + " FROM Taxe"
            + " WHERE idTaxe = ?";
    
    private static final String SQL_FIND_BY_EDITION = "SELECT"
            +" t.idTaxe, t.libelle, t.valeur"
            +" FROM Taxe AS t"
            +" JOIN ApplicationTaxe AS at ON at.idTaxe = t.idTaxe"
            +" WHERE at.idEdition = ?";
    
    public List<Taxe> findAll(ConnexionBean bc) {
        List<Taxe> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Taxe item = new Taxe();
                
                item.setId(rs.getLong("idTaxe"));
                item.setLibelle(rs.getString("Libelle"));
                item.setValeur(rs.getFloat("valeur"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    public Taxe findById(ConnexionBean bc, Long id) {
        Taxe item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                item = new Taxe();
                item.setId(rs.getLong("idTaxe"));
                item.setLibelle(rs.getString("Libelle"));
                item.setValeur(rs.getFloat("valeur"));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }
    
    public List<Taxe> findByEdition(ConnexionBean bc, Long idEdition) {
        List<Taxe> list = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_EDITION);
            
            ps.setLong(1, idEdition);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Taxe item = new Taxe();
                
                item.setId(rs.getLong("idTaxe"));
                item.setLibelle(rs.getString("Libelle"));
                item.setValeur(rs.getFloat("valeur"));
                
                list.add(item);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
}
