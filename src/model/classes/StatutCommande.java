package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives au statut d'une commande.
 * 
 */
public class StatutCommande implements Serializable{

    /**
     * Séquentiel de la table StatutCommande généré automatiquement par
     * la BDD.
     * 
     */
    private Long id;
    /**
     * Libellé du statut.
     * 
     * @see StatutAdresseDAO
     */
    private String libelle;
    /**
     * Code du Statut.
     * 
     * @see StatutAdresseDAO
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public StatutCommande() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "StatutCommande{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + '}';
    }

}
