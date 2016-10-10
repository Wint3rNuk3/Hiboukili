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
import model.classes.Adresse;
import model.classes.Editeur;
import model.classes.Edition;
import model.classes.Langue;
import model.classes.Ouvrage;
import model.classes.Promotion;
import model.classes.StatutEdition;
import model.classes.Taxe;

public class EditeurBean {

    private static final String SQL_FIND_ALL = "SELECT"
            + " idEditeur, idAdresse, libelle"
            + " FROM Editeur";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idEditeur, idAdresse, libelle"
            + " FROM Editeur"
            + " WHERE idEditeur = ?";

    public List<Editeur> findAll(ConnexionBean bc) {
        List<Editeur> list = new ArrayList();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();

            list = list(rs, bc);

        } catch (SQLException ex) {
            Logger.getLogger(EditeurBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public Editeur findById(ConnexionBean bc, Long id) {
        Editeur item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();
            
            item = one(rs, bc);
        } catch (SQLException ex) {
            Logger.getLogger(AdressesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return item;
    }

    private Editeur map(ResultSet rs, ConnexionBean bc) throws SQLException {
        Editeur editeur = new Editeur();

        Long idEdition = rs.getLong("idEditeur");
        Long idAdresse = rs.getLong("idAdresse");

        editeur.setId(idEdition);
        editeur.setLibelle(rs.getString("libelle"));
        
        // recuperer l'ouvrage.
        Adresse adresse = new AdressesBean().findById(bc, idAdresse);
        editeur.setAdresse(adresse);

        return editeur;
    }

    private List<Editeur> list(ResultSet rs, ConnexionBean bc) throws SQLException {
        List<Editeur> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs, bc));
        }
        
        return list;
    }

    private Editeur one(ResultSet rs, ConnexionBean bc) throws SQLException {
        if(rs.next()) {
            return map(rs, bc);
        }
        
        return null;
    }

//    public List<Edition> paginate(BeanConnexion bc, int page, int perPage){
//        List<Edition> list = findAll(bc);
//        
//        return list.subList(
//                Math.max(page * perPage - perPage, 0), 
//                Math.min(page * perPage, list.size()));
//    }
}
