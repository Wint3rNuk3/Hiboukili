
package model.beans;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import javax.sql.DataSource;
import model.classes.Commande;



public class OrderBean implements Serializable {
    
   HashMap<String, Commande>map;

    public OrderBean(){
      this.map=new HashMap();
      
        
    }
    
    
    
    
    public void create(ShoppingCartBean cart, ConnexionBean bc){
        //methode pour afficher les info general de la commande a partir du panier de l'utilisateur

        cart.getCartPrice();
        //cart.qtyTotalCart();
        recupererStatutCommande(bc);
        
        
    }
    
    
    public void create(){
        /* Cree la commande avec  : 
        - adresselivraison
        - adressefactuation
        -utilisateur
        -numero commande
        - date commande*/
    }
    public void save(){
        //recuperer la hasmap commande 
        //appel de la methode create 2 
        //Insert into commande
        //sauvegarder la commande en base de donnée : 
        //pour cela il faut add la map avec : 
        // - idAdresseLivraison ( choix de l'utilisateur dans la combo) 
        // - idAdresseFacturation ( la meme)
        // - id Utilisateur ( recuperer le login user )
        // - numero commande ( gestion sql newid()
        // - dateCommande ( demaner au controller de recuperer la date courante)
        //envoyer tout ca Insert into commande.
        
    }
    public void modifier(){
        // modifier et supprimer : 
        // retour au panier
    }
    
    
    
    public String recupererStatutCommande(ConnexionBean bc) {
        int i = 0;
        String statCommande = null;

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {
            
            String query = "SELECT libelle FROM StatutCommande WHERE code = 'cp1'";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
            statCommande = (rs.getString("libelle"));
            }
            rs.close();
            stmt.close();
            
        } catch (SQLException ex) {
            System.err.println("Erreur dans Statut commande" + ex.getMessage());
        }

        ds = bc.MaConnexion();
        
        
        return statCommande;
       
    }


    public void recupererUtilisateur(){
        
    }
    
    public Collection<Commande> list() {
        return map.values();
    }
    public int size() {
        return map.size();
    }
    public boolean isEmpty() { 
        return map.isEmpty();
    }
    public void clean() {
        map.clear();
        //apres la validation final : clean map
    }
    
   
    

    
  
    
    
}
