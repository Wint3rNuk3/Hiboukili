package model.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.classes.Editeur;
import model.classes.Edition;
import model.classes.Langue;
import model.classes.Ouvrage;
import model.classes.Promotion;
import model.classes.StatutEdition;
import model.classes.Taxe;

public class EditionBean {

    private static final String SQL_FIND_ALL = "SELECT"
            + " idEdition, isbn, idOuvrage, idEditeur, idLangue,"
            + " idStatutEdition, datePubli, prixHt,"
            + " couverture, titre, stock"
            + " FROM Edition";

    private static final String SQL_FIND_BY_RUBRIQUE = "SELECT"
            + " e.idEdition, e.isbn, e.idOuvrage, e.idEditeur, e.idLangue,"
            + " e.idStatutEdition, e.datePubli, e.prixHt,"
            + " e.couverture, e.titre, e.stock"
            + " FROM Edition as e"
            + " JOIN MiseEnRubrique AS mer ON mer.idOuvrage = e.idOuvrage"
            + " WHERE mer.idRubrique = ?";
    
    // on recherche par 
    //  - le titre ( tout ou partie )
    //  - mot clef
    //  - la rubrique
    //  - le theme
    //  - le resume ? 
    //  - l'auteur ( nom, prenom )
    private static final String SQL_RECHERCHE = "SELECT DISTINCT"
            + " e.idEdition, e.isbn, e.idOuvrage, e.idEditeur, e.idLangue,"
            + " e.idStatutEdition, e.datePubli, e.prixHt,"
            + " e.couverture, e.titre, e.stock"
            + " FROM Edition AS e"
            
            + " JOIN Ouvrage        AS ouv  ON ouv.idOuvrage  = e.idOuvrage"
            + " JOIN Auteur         AS aut  ON ouv.idOuvrage  = e.idOuvrage"
            
            + " JOIN MiseEnRubrique AS mer  ON mer.idOuvrage  = e.idOuvrage"
            + " JOIN Rubrique       AS rub  ON mer.idRubrique = rub.idRubrique"
            
            + " JOIN Thematique     AS thq  ON thq.idOuvrage  = e.idOuvrage"
            + " JOIN Theme          AS the  ON thq.idTheme    = the.idTheme"
            
            + " JOIN Referencement  AS ref  ON ref.idOuvrage  = e.idOuvrage"
            + " JOIN Tag            AS tag  ON ref.idTag      = tag.idTag"
            
            + " WHERE ouv.titre like ? OR e.titre like ?"
//            + " OR ";
    ;

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
            + " idEdition, isbn, idOuvrage, idEditeur, idLangue,"
            + " idStatutEdition, datePubli, prixHt,"
            + " couverture, titre, stock"
            + " FROM Edition"
            + " WHERE isbn=?";

    public Edition findByIsbn(ConnexionBean bc, String isbn) {
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
        Long idEditeur = rs.getLong("idEditeur");
        Long idLangue = rs.getLong("idLangue");
        Long idStatut = rs.getLong("idStatutEdition");

        edition.setId(idEdition);
        edition.setIsbn(rs.getString("isbn"));
        edition.setDatePublication(rs.getDate("datePubli"));
        edition.setPrixHt(rs.getFloat("prixHt"));
        edition.setCouverture(rs.getString("couverture"));
        edition.setTitre(rs.getString("titre"));
        edition.setStock(rs.getInt("stock"));

        // recuperer l'editeur.
        Editeur editeur = new EditeurBean().findById(bc, idEditeur);
        edition.setEditeur(editeur);

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
        if (rs.next()) {
            return map(rs, bc);
        }

        return null;
    }

    private static final String SQL_GET_STOCK = "SELECT"
            + " stock"
            + " FROM Edition"
            + " WHERE isbn = ?";

    private static final String SQL_SET_STOCK = "UPDATE Edition"
            + " SET stock = ?"
            + " WHERE isbn = ?";

    public void setStockInDB(ConnexionBean bc, Edition e) {
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps1 = c.prepareStatement(SQL_GET_STOCK);
            ps1.setString(1, e.getIsbn());
            ResultSet rs1 = ps1.executeQuery();
            String leStock = null;
            
            // les entrailles
            ResultSetMetaData rsmd = rs1.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            while (rs1.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = rs1.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                }
                leStock = rs1.getString(1);
                System.out.println("");
            }

            ///////////////////////////////////////////////////////
            int leStockVersionIntegreSaMere = Integer.parseInt(leStock);
            PreparedStatement ps2 = c.prepareStatement(SQL_SET_STOCK);
            if(e.getCartQty() > leStockVersionIntegreSaMere)
                ps2.setInt(1, 0);
                //eventuellement appeler une erreur
            else
                ps2.setInt(1, leStockVersionIntegreSaMere - e.getCartQty());
            
            ps2.setString(2, e.getIsbn());
            ps2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setStockInDB(ConnexionBean bc, String isbn, int qty) {
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps1 = c.prepareStatement(SQL_GET_STOCK);
            ps1.setString(1, isbn);
            ResultSet rs1 = ps1.executeQuery();
            String leStock = null;
            
            // les entrailles
            ResultSetMetaData rsmd = rs1.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            while (rs1.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = rs1.getString(i);
                    System.out.print(rsmd.getColumnName(i) + " : " + columnValue);
                }
                leStock = rs1.getString(1);
                System.out.println("");
            }

            ///////////////////////////////////////////////////////
            int leStockVersionIntegreSaMere = Integer.parseInt(leStock);
            PreparedStatement ps2 = c.prepareStatement(SQL_SET_STOCK);
            
            ps2.setInt(1, leStockVersionIntegreSaMere + qty);
            
            ps2.setString(2, isbn);
            ps2.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Edition> recherche(ConnexionBean bc, String q) {
        List<Edition> list = new ArrayList();

        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_RECHERCHE);

            ps.setString(1, "%" + q + "%");
            ps.setString(2, "%" + q + "%");

            ResultSet rs = ps.executeQuery();

            list = list(rs, bc);

        } catch (SQLException ex) {
            Logger.getLogger(EditionBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    

//    public List<Edition> paginate(BeanConnexion bc, int page, int perPage){
//        List<Edition> list = findAll(bc);
//        
//        return list.subList(
//                Math.max(page * perPage - perPage, 0), 
//                Math.min(page * perPage, list.size()));
//    }
}
