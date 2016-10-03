package model.classes;

/**
 * Enregistre les différentes info. relatives au statut de l'entreprise.
 *
 */
public class StatutEntreprise {

    /**
     * Séquentiel de la table StatutEntreprise généré automatiquement par la
     * BDD.
     */
    private Long id;
    /**
     * Libellé du statut.
     *
     * @see StatutEntrepriseDAO
     */
    private String libelle;
    /**
     * N° de code du Statut.
     *
     * @see StatutEntrepriseDAO
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public StatutEntreprise() {
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
