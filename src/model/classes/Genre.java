package model.classes;

import java.io.Serializable;
import java.util.Objects;

/**
 * Enregistre les différetes info. relatives au genre d'un ouvrage.
 */
public class Genre implements Serializable{

    /**
     * Séquentiel de la table Genre généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom du genre.
     */
    private String libelle;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Genre() {
    }

    
        /**
     * Constructeur de la classe avec paramètres;
     * permet l'appel au constructeur en spécifiant les paramètres.
     *
     * @param id
     * @param libelle
     */
    public Genre(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    public String getLibelle() {
        return libelle;
    }

    /**
     * Retourne le genre.
     *
     * @return Genre.
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * Met à jour le genre.
     *
     * @param libelle Nouveau Genre.
     */
    @Override
    public String toString() {
        return libelle;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Genre other = (Genre) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
