package model.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.sql.DataSource;
import model.classes.Adresse;

public class AdressesBean {

    //ArrayList adresses;
    //HashMap<String, ArrayList<Adresse>> map;
    HashMap<String, Adresse> map;

    public AdressesBean() {
        this.map = new HashMap();
        //this.adresses = new ArrayList();
    }

    public void recupererAdresse(ConnexionBean bc) {
        int i = 0;

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {

            String query = "SELECT numero, voie, codePostal, ville, complement FROM ADRESSE";
            Statement stmt = c.createStatement();
            Adresse adresse = new Adresse();

            ResultSet rs = stmt.executeQuery(query);
            

            while (rs.next()) {
                map.put("Adresse", new Adresse(rs.getString("numero"), rs.getString("voie"), rs.getString("codePostal"), rs.getString("ville"), rs.getString("complement")));

            }
           // Solution 1
//            while(rs.next()){
//                adresses.add(rs.getString("numero")
//                        + rs.getString("voie")
//                        + rs.getString("codePostal")
//                        + rs.getString("ville")
//                        + rs.getString("complement"));
//
//                       
//            }
//            
//          map.put("Adresse", adresses);

            System.out.println("Contenu :" + map);

           
            rs.close();
            stmt.close();
//            
        } catch (SQLException ex) {
            System.err.println("Erreur dans Adresse" + ex.getMessage());
        }

        ds = bc.MaConnexion();

       
    }

    public Collection<Adresse> list() {
        return map.values();

    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

}
