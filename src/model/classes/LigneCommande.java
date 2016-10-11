package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives à chaque ligne d'une commande.
 * Cette classe a pour objet de "figer" les informations relatives à la commande
 * de chaque article; ainsi, si le prix d'un article change au cours d'une
 * commande, voire après, cette classe permet de conserver le prix de l'article
 * lors du passage de la commande.
 */
public class LigneCommande implements Serializable{

    /**
     * Séquentiel de la table LigneCommande généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Quantité d'un article commandé.
     */
    private int quantite;
    /**
     * Prix unitaire hors taxe de d'un article commandé.
     */
    private float prixUnit;
    /**
     * %age de la/des taxe(s) appliquée(s) sur un article par rapport au prix
     * hors taxe.
     */
    private float taxe;
    /**
     * Montant d'une réduction appliquée sur un article (%age ou valeur).
     */
    private float promo;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public LigneCommande() {
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(float prixUnit) {
        this.prixUnit = prixUnit;
    }

    public float getTaxe() {
        return taxe;
    }

    public void setTaxe(float taxe) {
        this.taxe = taxe;
    }

    public float getPromo() {
        return promo;
    }

    public void setPromo(float promo) {
        this.promo = promo;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", quantite=" + quantite + ", prixUnit=" + prixUnit + ", taxe=" + taxe + ", promo=" + promo + '}';
    }

}
