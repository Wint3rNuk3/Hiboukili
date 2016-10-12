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
import model.classes.Edition;

public class OrderBean implements Serializable {

    ArrayList commandes;
    private String statCommande;

    public OrderBean() {

        this.commandes = new ArrayList();

    }

    public String getStatCommande() {
        return statCommande;
    }

    public void setStatCommande(String statCommande) {
        this.statCommande = statCommande;
    }

//    public void save(ConnexionBean bc, Long idAdresseFacturation, Long idAdresseLivraison, Long idUtilisateur) {
//        DataSource ds = bc.MaConnexion();
//
//        try (Connection c = ds.getConnection();) {
//
//            String query = "DECLARE @guid varchar(50);"
//                    + " SET @guid=NEWID();"
//                    + " INSERT INTO Commande(idAdresseFacturation, idAdresseLivraison, idUtilisateur, numero, dateCommande)"
//                    + " VALUES (?, ?, ?, @guid, cast(convert(char(8), GETDATE(), 112) as int));";
//
//            PreparedStatement stmt = c.prepareStatement(query);
//  
//            stmt.setLong(1, idAdresseFacturation);
//            stmt.setLong(2, idAdresseLivraison);
//            stmt.setLong(3, idUtilisateur);
//            
//            stmt.close();
//            
//        }catch (SQLException ex) {
//            
//            System.err.println("Erreur dans Commande" + ex.getMessage());
//        }
//
//        ds = bc.MaConnexion();
//
//    }

    public void save(ConnexionBean bc, Long idAdresseFacturation, Long idAdresseLivraison, Long idUtilisateur) {
        int i = 0;

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {

            //requete SQL pour sauvegarder la commande dans la base de donnée.
            String query = "DECLARE @guid varchar(50);"
                    + " SET @guid= NEWID();"
                    + " INSERT INTO Commande(idAdresseFacturation, idAdresseLivraison, idUtilisateur, numero, dateCommande)"
                    + " SELECT ?,?,?,@guid, cast(convert(char(8), GETDATE(), 112) as int)"
                    + " FROM Utilisateur AS a"
                    + " INNER JOIN DernieresFacturations AS b"
                    + " ON a.IdUtilisateur=b.IdUtilisateur"
                    + " INNER JOIN DernieresLivraisons AS c"
                    + " ON a.IdUtilisateur=c.idUtilisateur";
            
            PreparedStatement stmt = c.prepareStatement(query);

            stmt.setLong(1, idAdresseFacturation);
            stmt.setLong(2, idAdresseLivraison);
            stmt.setLong(3, idUtilisateur);

            int result = stmt.executeUpdate();
System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ result+"/"+idAdresseFacturation
+"/"+idAdresseLivraison
+"/"+idUtilisateur);

            stmt.close();

        } catch (SQLException ex) {
            System.err.println("Erreur dans Commande" + ex.getMessage());
        }

        ds = bc.MaConnexion();

    }
    public void modifier() {
        // modifier et supprimer : 
        // retour au panier
    }

    public String recupererStatutCommande(ConnexionBean bc) {

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

    public void recupererNumerosCommande(ConnexionBean bc, Long idUtilisateur) {

        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection();) {

            String query = "SELECT TOP 1 numero FROM Commande WHERE idUtilisateur = ? ORDER BY dateCommande";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setLong(1, idUtilisateur);

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                Commande com = new Commande(res.getString("numero"));
                commandes.add(com);
            }

            res.close();
            stmt.close();

        } catch (SQLException ex) {
            System.err.println("Erreur dans Statut commande" + ex.getMessage());
        }

        ds = bc.MaConnexion();

    }

    public Collection<Commande> list() {
        return commandes;
    }

    public int size() {
        return commandes.size();
    }

    public boolean isEmpty() {
        return commandes.isEmpty();
    }

    public void clean() {
        commandes.clear();
        //apres la validation final : clean map
    }

}
