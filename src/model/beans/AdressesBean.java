package model.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.classes.Adresse;
import model.classes.Pays;
import model.classes.StatutAdresse;

public class AdressesBean {
    
    private final String SQL_FIND_ALL = "SELECT"
            + " idAdresse, idPays, idStatutAdresse, numero, voie, codePostal, ville, complement"
            + " FROM Adresse";
    
    private final String SQL_FIND_BY_ID = "SELECT"
            + " idAdresse, idPays, idStatutAdresse, numero, voie, codePostal, ville, complement"
            + " FROM Adresse"
            + " WHERE idAdresse = ?";

    ArrayList adresses;
    
    
    public AdressesBean() {
        
        this.adresses = new ArrayList();
    }
    
    public List<Adresse> findAll(ConnexionBean bc) {
        List<Adresse> list = new ArrayList();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();

            list = list(rs, bc);

        } catch (SQLException ex) {
            Logger.getLogger(AdressesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    public Adresse findById(ConnexionBean bc, Long id) {
        Adresse item = null;

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

    private Adresse map(ResultSet rs, ConnexionBean bc) throws SQLException {
        Adresse addr = new Adresse();

        Long idAdresse = rs.getLong("idAdresse");
        Long idPays = rs.getLong("idPays");
        Long idStatut = rs.getLong("idStatutAdresse");

        addr.setId(idAdresse);
        addr.setComplement(rs.getString("complement"));
        addr.setCp(rs.getString("codePostal"));
        addr.setNumero(rs.getString("numero"));
        addr.setVille(rs.getString("ville"));
        addr.setVoie(rs.getString("voie"));
        
        Pays pays = new PaysBean().findById(bc, idPays);
        addr.setPays(pays);
        
        StatutAdresse statut = new StatutAdresseBean().findById(bc, idStatut);
        addr.setStatut(statut);
        
        return addr;
    }

    private List<Adresse> list(ResultSet rs, ConnexionBean bc) throws SQLException {
        List<Adresse> list = new ArrayList();
        
        while (rs.next()) {
            list.add(map(rs, bc));
        }
        
        return list;
    }

    private Adresse one(ResultSet rs, ConnexionBean bc) throws SQLException {
        if(rs.next()) {
            return map(rs, bc);
        }
        
        return null;
    }

    public void recupererAdresse(ConnexionBean bc) {
        int i = 0;

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {

            String query = "SELECT idAdresse, numero, voie, codePostal, ville, complement FROM ADRESSE";
            Statement stmt = c.createStatement();
            

            ResultSet rs = stmt.executeQuery(query);
            

            while (rs.next()) {
                Adresse a= new Adresse(rs.getString("numero"), rs.getString("voie"), rs.getString("codePostal"), rs.getString("ville"), rs.getString("complement"));
                a.setId( rs.getLong("idAdresse"));
                adresses.add( a);

            }
          
           
            rs.close();
            stmt.close();
//            
        } catch (SQLException ex) {
            System.err.println("Erreur dans Adresse" + ex.getMessage());
        }

        ds = bc.MaConnexion();

       
    }

    public Collection<Adresse> list() {
        return adresses;

    }

    public int size() {
        return adresses.size();
    }

    public boolean isEmpty() {
        return adresses.isEmpty();
    }

}
