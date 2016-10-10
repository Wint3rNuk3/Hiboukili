
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
    
    ArrayList adresses;
    //HashMap<String, ArrayList<Adresse>> map;
    HashMap<String, Adresse>map;

    public AdressesBean() {
        this.map= new HashMap();
        this.adresses = new ArrayList();
    }
    
     public void recupererAdresse(ConnexionBean bc){
        int i = 0;

        DataSource ds = bc.MaConnexion();
        
        
        try (Connection c = ds.getConnection();){
        

            String query = "SELECT numero, voie, codePostal, ville, complement FROM ADRESSE";
            Statement stmt = c.createStatement();
            Adresse adresse = new Adresse();
            
            ResultSet rs= stmt.executeQuery(query);
            // S2 ResultSet rs_SubItemType = stmt.executeQuery(query);
            
            while(rs.next()){
                map.put("Adresse" , new Adresse(rs.getString("numero"),rs.getString("voie"),rs.getString("codePostal"),rs.getString("ville"),rs.getString("complement")));
                
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
            
            System.out.println("Contenu :"+map);
            
            //SOlution 2 
//            ResultSetMetaData metaData = rs_SubItemType.getMetaData();
//            int colCount = metaData.getColumnCount();
//            Map<String, Object>columns = new HashMap<String, Object>();
//            while(rs_SubItemType.next()){
//                
//                for(int i =1; i<= colCount; i++){
//                    columns.put(metaData.getColumnLabel(i), rs_SubItemType.getObject(i));
//                }
//                
//                adresse.add(columns);
//            }
//           
//            
//            System.out.println(columns);  
//
//           //S2 rs_SubItemType.close();
                rs.close();
            stmt.close();
//            
        } catch (SQLException ex) {
            System.err.println("Erreur dans Adresse" + ex.getMessage());
        }

        ds = bc.MaConnexion();
        
       // System.out.println(map);
 
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
