package model.classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les différentes info. relatives à une promotion.
 *
 */
public class Promotion implements Serializable{

    /**
     * Séquentiel de la table Promotion généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Date d'entrée en vigueur de la promotion.
     */
    private Date dateDebut;
    /**
     * Date de fin de la promotion.
     */
    private Date dateFin;
    /**
     * Montant de la réduction (%age ou valeur).
     */
    private Float reduction;
    /**
     * String spécifiant le chemin d'accès à l'image de la promotion.
     */
    private String image;
    /**
     * Info. relatives à la promotion.
     */
    private String description;
    /**
     * Commentaires relatifs à la promotion.
     */
    private String commentaire;
    /**
     * Valeur appliquée en %age.
     */
    private float valeur;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Promotion() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Float getReduction() {
        return reduction;
    }

    public void setReduction(Float reduction) {
        this.reduction = reduction;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public float getValeur() {
        return valeur;
    }

    public void setValeur(float valeur) {
        this.valeur = valeur;
    }
    
    

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", reduction=" + reduction + ", image=" + image + ", description=" + description + ", commentaire=" + commentaire + '}';
    }

}
