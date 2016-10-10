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
import model.classes.Promotion;
import model.classes.StatutEdition;
import model.classes.Taxe;

public class EditionBean {

    private static final String SQL_FIND_ALL = "SELECT"
            + " idEdition, isbn, idOuvrage, idLangue,"
            + " idStatutEdition, datePubli, prixHt,"
            + " couverture, titre, stock"
            + " FROM Edition";
    
    private static final String SQL_FIND_BY_RUBRIQUE = "SELECT"
            + " e.idEdition, e.isbn, e.idOuvrage, e.idLangue,"
            + " e.idStatutEdition, e.datePubli, e.prixHt,"
            + " e.couverture, e.titre, e.stock"
            + " FROM Edition as e"
            + " JOIN MiseEnRubrique AS mer ON mer.idOuvrage = e.idOuvrage"
            + " WHERE mer.idRubrique = ?";

    public List<Edition> findAll(ConnexionBean bc) {
        List<Edition> list = new ArrayList();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();

            list = list(rs, bc);

        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public List<Edition> findByRubrique(ConnexionBean bc, Long idRubrique) {
        List<Edition> list = new ArrayList();

        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_RUBRIQUE);
            
            ps.setLong(1, idRubrique);
            
            ResultSet rs = ps.executeQuery();

            list = list(rs, bc);

        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    private static final String SQL_FIND_BY_ISBN = "SELECT"
            + " idEdition, isbn, idOuvrage, idLangue,"
            + " idStatutEdition, datePubli, prixHt,"
            + " couverture, titre, stock"
            + " FROM Edition"
            + " WHERE isbn=?";

    
    public Edition findByIsbn(ConnexionBean bc, String isbn){
        Edition edition = new Edition();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.

        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ISBN);
            ps.setString(1, isbn);
            ResultSet rs = ps.executeQuery();
            //String executedQuery = rs.getStatement().toString();
            //System.out.println(executedQuery);
            
            edition = one(rs, bc);
                
                // mettre à jour le prix.
                // on ne veut afficher le prix ttc seulement lors de l'affichage de la commande ou du panier ?
//                edition.initPrix();
            
        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(edition);
        return edition;
    }

    private Edition map(ResultSet rs, ConnexionBean bc) throws SQLException {
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
        
        // recuperer les promotions.
        List<Promotion> promos = new PromotionBean().findByEdition(bc, idEdition);
        edition.setPromotions(promos);
        
        return edition;
    }

    private List<Edition> list(ResultSet rs, ConnexionBean bc) throws SQLException {
        List<Edition> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs, bc));
        }
        
        return list;
    }

    private Edition one(ResultSet rs, ConnexionBean bc) throws SQLException {
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
