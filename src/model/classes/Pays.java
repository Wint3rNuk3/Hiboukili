package model.classes;

import java.util.Objects;

/**
 * Enregistre les différentes info. relatives à un pays.
 *
 */
public class Pays {

    /**
     * Séquentiel de la table Pays généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom du pays
     */
    private String libelle;
    /**
     * Code du pays.
     */
    private String code;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Pays() {
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
        return "Pays{" + "id=" + id + ", libelle=" + libelle + ", code=" + code + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Pays other = (Pays) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
