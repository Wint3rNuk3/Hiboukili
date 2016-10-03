package model.classes;

import java.util.Objects;

/**
 * Enregistre les différetes info. relatives au thèmes d'un ouvrage.
 */
public class Theme {

    /**
     * Séquentiel de la table Theme généré automatiquement par la BDD.
     */
    private Long id;

    /**
     * Nom du thème.
     */
    private String libelle;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Theme() {
    }

    public Theme(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
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

    @Override
    public String toString() {
//        return "Theme{" + "id=" + id + ", libelle=" + libelle + '}';
        return libelle;
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
        final Theme other = (Theme) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
