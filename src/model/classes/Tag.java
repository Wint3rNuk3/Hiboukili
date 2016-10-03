package model.classes;

import java.util.Objects;

/**
 * Enregistre les différentes info. relatives aux mots-clefs utilisés pour
 * résumer un ouvrage.
 *
 */
public class Tag {

    /**
     * Séquentiel de la table Tag généré automatiquement par la
     * BDD.
     */
    private Long id;
        /**
     * Libellé du mot-clef.
     *
     */
    private String libelle;

        /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Tag() {}
    
    public Tag(String libelle) {
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
//        return "Tag{" + "id=" + id + ", libelle=" + libelle + '}';
        return libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.libelle);
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
        final Tag other = (Tag) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

}
