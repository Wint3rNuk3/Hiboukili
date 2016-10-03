package model.classes;

/**
 * Enregistre les différentes info. relatives au statut d'une adresse.
 *
 */
public class StatutAdresse {

    /**
     * Séquentiel de la table StatutAdresse généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Libellé du statut.
     * 
     * @see StatutAdresseDAO
     */
    private String libelle;
    /**
     * N° de code du Statut.
     * 
     * @see StatutAdresseDAO
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public StatutAdresse() {
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
        return "StatutAdresse{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + '}';
    }

}
