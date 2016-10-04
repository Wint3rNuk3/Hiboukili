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
import model.classes.Edition;
import model.classes.Langue;
import model.classes.Ouvrage;
import model.classes.StatutEdition;
import model.classes.Taxe;

public class EditionBean {
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idEdition, isbn, idOuvrage, idLangue,"
            + " idStatutEdition, datePubli, prixHt,"
            + " couverture, titre, stock"
            + " FROM Edition";

    
    public List<Edition> findAll(BeanConnexion bc){
        List<Edition> list = new ArrayList();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Edition edition = new Edition();
                
                Long idEdition = rs.getLong("idEdition");
                Long idOuvrage = rs.getLong("idOuvrage");
                Long idLangue = rs.getLong("idLangue");
                Long idStatut = rs.getLong("idStatutEdition");
                
                edition.setId(idEdition);
                edition.setIsbn(rs.getString("isbn"));
                edition.setDatePublication(rs.getDate("datePubli"));
                edition.setPrixHt(rs.getFloat("prixHt"));
                edition.setCouverture(rs.getString("couverture"));
                edition.setTitre(rs.getString("titre"));
                edition.setStock(rs.getInt("stock"));
                
                // recuperer l'ouvrage.
                Ouvrage ouvrage = new OuvrageBean().findById(bc, idOuvrage);
                edition.setOuvrage(ouvrage);
                
                // recuperer la langue.
                Langue langue = new LangueBean().findById(bc, idLangue);
                edition.setLangue(langue);
                
                // recupere le statut edition.
                StatutEdition findById = new StatutEditionBean().findById(bc, idStatut);
                edition.setStatut(findById);
                
                // recuperer les taxes.
                List<Taxe> taxes = new TaxeBean().findByEdition(bc, idEdition);
                edition.setTaxes(taxes);
                
                list.add(edition);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
}