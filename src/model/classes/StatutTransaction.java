package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives au statut d'une transaction.
 *
 */
public class StatutTransaction implements Serializable{

    /**
     * Séquentiel de la table StatutTransaction généré automatiquement par la
     * BDD.
     */
    private Long id;
    /**
     * Libellé du statut.
     *
     * @see StatutTransactionDAO
     */
    private String libelle;
    /**
     * Code Statut.
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public StatutTransaction() {
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
        return "StatutTransaction{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + '}';
    }

}
