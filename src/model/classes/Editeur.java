package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives à l'Editeur d'une Edition.
 */
public class Editeur implements Serializable{

    /**
     * Séquentiel de la table Editeur généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom de l'éditeur.
     */
    private String libelle;
    
    private Adresse adresse;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Editeur() {
    }

    /**
     * Retourne l'id (généré par la BDD).
     *
     * @return Id (généré par la BDD) sous la forme d'un entier (long).
     */
    public Long getId() {
        return id;
    }

    /**
     * Met à jour l'id (généré par la BDD).
     *
     * @param id Nouvel id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de l'éditeur.
     *
     * @return Nom de l'éditeur.
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * Met à jour le nom de l'éditeur.
     *
     * @param libelle Nouveau nom de l'éditeur.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    @Override
    public String toString() {
        return "Editeur{" + "id=" + id + ", libelle=" + libelle + '}';
    }

}
