package model.classes;

import java.io.Serializable;
import java.sql.Date;

/**
 * Enregistre les différentes info. relatives au passage d'une commande.
 */
public class HistoriqueStatutCommande implements Serializable{

    /**
     * Séquentiel de la table HistoriqueStatutCommande généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Date de passage de la commande.
     */
    private Date datePassage;
    /**
     * Commentaire interne éventuel concernant la commande.
     */
    private String commentaire;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public HistoriqueStatutCommande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(Date datePassage) {
        this.datePassage = datePassage;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "HistoriqueStatutCommande{" + "id=" + id + ", datePassage=" + datePassage + ", commentaire=" + commentaire + '}';
    }

}
