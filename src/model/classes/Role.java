package model.classes;

import java.io.Serializable;

/**
 * Distingue les employés des clients.
 */
public class Role implements Serializable{

    /**
     * Séquentiel de la table Role généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Statut utilisateur
     */
    private String libelle;
    /**
     * Code utilisateur.
     */
    private String code;

    public Role() {
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

}
