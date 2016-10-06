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
import model.classes.Rubrique;

public class RubriqueBean {
    
    private static final Logger LOG = Logger.getLogger(RubriqueBean.class.getName());
    

    private static final String SQL_FIND_ALL = "SELECT"
            + " idRubrique, libelle, date_debut, date_fin, commentaire"
            + " FROM Rubrique";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idRubrique, libelle, date_debut, date_fin, commentaire"
            + " FROM Rubrique"
            + " WHERE idRubrique = ?";
    
    private static final String SQL_FIND_BY_OUVRAGE = "SELECT"
            + " r.idRubrique, r.libelle, r.date_debut, r.date_fin, r.commentaire"
            + " FROM Rubrique AS r"
            + " JOIN MiseEnRubrique AS mer on r.idRubrique = mer.idRubrique"
            + " WHERE mer.idOuvrage = ?";
    
    public Rubrique findById(BeanConnexion bc, Long id) {
        Rubrique item = null;

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
    
    public List<Rubrique> findAll(BeanConnexion bc) {
        List<Rubrique> list = new ArrayList<>();
        
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
    
    public List<Rubrique> findByOuvrage(BeanConnexion bc, Long idOuvrage) {
        List<Rubrique> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_OUVRAGE);
            ps.setLong(1, idOuvrage);
            ResultSet rs = ps.executeQuery();

            list = list(rs);
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    protected Rubrique map(ResultSet rs) throws SQLException {
        
        Rubrique item = new Rubrique(); 
        
        item.setId(rs.getLong("idRubrique"));
        item.setLibelle(rs.getString("libelle"));
        item.setDateDebut(rs.getDate("date_debut"));
        item.setDateFin(rs.getDate("date_fin"));
        item.setCommentaire(rs.getString("commentaire"));
        
        return item;
    }
    
    private List<Rubrique> list(ResultSet rs) throws SQLException {
        List<Rubrique> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs));
        }
        
        return list;
    }

    private Rubrique one(ResultSet rs) throws SQLException {
        if(rs.next()) {
            return map(rs);
        }
        
        return null;
    }

}
