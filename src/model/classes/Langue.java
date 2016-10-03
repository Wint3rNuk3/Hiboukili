
package model.classes;

/**
 * Enregistre les différentes info. relatives à la langue dans laquelle
 * a été rédigé une édition.
 */
public class Langue {
    
        /**
     * Séquentiel de la table Langue généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Langue de l'ouvrage.
     */
    private String libelle;
    /**
     * Code de la langue.
     */
    private String code;

        /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Langue() {
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
//        return "Langue{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + '}';
        return libelle;
    }
    
    
    
}
