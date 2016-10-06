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
import model.classes.Promotion;

public class PromotionBean {
    private static final Logger LOG = Logger.getLogger(PromotionBean.class.getName());
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idPromo, dateDebut, dateFin, reduction, image, description, commentaire"
            + " FROM Promotion";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idPromo, dateDebut, dateFin, reduction, image, description, commentaire"
            + " FROM Promotion"
            + " WHERE idPromo = ?";
    
    private static final String SQL_FIND_BY_EDITION = "SELECT"
            + " p.idPromo, p.dateDebut, p.dateFin,"
            + " p.reduction, p.image, p.description, p.commentaire"
            + " FROM Promotion AS p"
            + " JOIN ApplicationPromo AS ap ON p.idPromo = ap.idPromo"
            + " WHERE ap.idEdition = ?";
    
    public List<Promotion> findAll(ConnexionBean bc) {
        List<Promotion> list = new ArrayList<>();

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

    public Promotion findById(ConnexionBean bc, Long id) {
        Promotion item = null;

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
    
    public List<Promotion> findByEdition(ConnexionBean bc, Long idEdition) {
        List<Promotion> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_EDITION);
            
            ps.setLong(1, idEdition);
            
            ResultSet rs = ps.executeQuery();
            
            list = list(rs);
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }
    
    protected Promotion map(ResultSet resultSet) throws SQLException {
        
        Promotion promo = new Promotion(); 
        
        promo.setId(resultSet.getLong("idPromo"));
        promo.setCommentaire(resultSet.getString("commentaire"));
        promo.setDateDebut(resultSet.getDate("dateDebut"));
        promo.setDateFin(resultSet.getDate("dateFin"));
        promo.setDescription(resultSet.getString("description"));
        promo.setImage(resultSet.getString("image"));
        promo.setReduction(resultSet.getFloat("reduction"));
        
        return promo;
    }
    
    private List<Promotion> list(ResultSet rs) throws SQLException {
        List<Promotion> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs));
        }
        
        return list;
    }

    private Promotion one(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return map(rs);
        }
        
        return null;
    }
}
