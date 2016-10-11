
package model.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import model.beans.ConnexionBean;
import model.beans.ShoppingCartBean;

public class PanierTotal {
    
    private int qtyTotal;
    private float prixTotal;
    private String statutCommande;
    private ShoppingCartBean cart;
    

    public PanierTotal() {
    }

    public PanierTotal(int qtyTotal, float prixTotal, String statutCommande) {
        this.qtyTotal = qtyTotal;
        this.prixTotal = prixTotal;
        this.statutCommande = statutCommande;
    }

    public int getQtyTotal() {
        int qtyTotal = 0;
        for(Edition e : cart.list()){
            
            qtyTotal += e.getCartQty();
        }
        return qtyTotal;
    }

    public void setQtyTotal(int qtyTotal) {
        this.qtyTotal = qtyTotal;
    }

    public float getPrixTotal() {
        cart.getCartPrice();
        return prixTotal;
    }

    public void setPrixTotal(float prixTotal) {
        this.prixTotal = prixTotal;
    }

    public String getStatutCommande(ConnexionBean bc) {
        recupererStatutCommande(bc);
        return statutCommande;
    }

    public void setStatutCommande(String statutCommande) {
        this.statutCommande = statutCommande;
    }

    public ShoppingCartBean getCart() {
        return cart;
    }

    public void setCart(ShoppingCartBean cart) {
        this.cart = cart;
    }

    public String recupererStatutCommande(ConnexionBean bc) {
        int i = 0;
        String statCommande = null;

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {

            String query = "SELECT libelle FROM StatutCommande WHERE code = 'cp1'";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
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
    


    
    
    
}
