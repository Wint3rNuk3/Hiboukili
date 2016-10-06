package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives au statut d'un utilisateur.
 *
 */
public class StatutUtilisateur implements Serializable{

    /**
     * Séquentiel de la table StatutUtilisateur généré automatiquement par la
     * BDD.
     */
    private Long id;
    /**
     * Libellé du statut.
     *
     * @see StatutUtilisateurDAO
     */
    private String libelle;
    /**
     * N° de code du Statut.
     *
     * @see StatutUtilisateurDAO
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public StatutUtilisateur() {
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
