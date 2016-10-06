
package model.beans;


import model.classes.Pays;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import javax.sql.DataSource;

public class ListePaysBean implements Serializable{
    
    HashMap map = null;
    public ListePaysBean(){
        map = new HashMap();
    }
    
    public void setMap(String key, String value){
        map.put(key, value);
    }
    
    public Collection returnMapValues(){
        return map.values();
    }

        public Collection returnMapKeys(){
        return map.keySet();
    }
        
        public HashMap returnHashMap(){
        return map;
    }        

    public void getListFromBdd(DataSource ds, ConnexionBean bc){
        ds=bc.MaConnexion();//connexion
        try(Connection c = ds.getConnection()){
            //extraction de l'idPays et libelle
            String query = "select idPays, libelle from Pays order by libelle";
            PreparedStatement ps = c.prepareStatement(query);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){//pour toutes les entrees de la table pays ds la bdd
                Pays cp = new Pays();// objet ClassePays avec 2 proprietes : idPays et Libelle
                cp.setId(rs.getLong("idPays"));
                cp.setLibelle(rs.getString("libelle"));
                
                map.put(rs.getString("libelle"), cp);//enregistrement des objets ClassePays ds une map 
            }
        }catch(SQLException ex){
            System.err.println("Erreur BeanListePays - "+ex.getMessage() );
        }
        
    }
}