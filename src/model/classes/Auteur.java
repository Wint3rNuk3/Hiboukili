package model.classes;

import java.sql.Date;
import java.util.Objects;

/**
 * Enregistre les différentes info. relatives à l'auteur d'un ouvrage.
 *
 * @see Ouvrage
 */
public class Auteur {

    /**
     * Séquentiel de la table Auteur généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom de l'auteur.
     */
    private String nom;
    /**
     * Prénom, s'il y a, lieu.
     */
    private String prenom;
    /**
     * Date de naissance : permettra par la suite, au besoin, de catégoriser un
     * auteur selon son époque
     */
    private Date dateNaissance;
    /**
     * Date de décès, s'il y a, lieu.
     */
    private Date dateDeces;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Auteur() {
    }

    /**
     * Constructeur de la classe avec paramètres;
     * permet l'appel au constructeur en spécifiant les paramètres.
     *
     * @param id
     * @param nom
     * @param dateNaissance
     * @param dateDeces
     */
    public Auteur(Long id, String nom, Date dateNaissance, Date dateDeces) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.dateDeces = dateDeces;
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
     * Retourne le nom.
     *
     * @return Nom.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Met à jour le nom.
     *
     * @param nom Nouveau nom.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne le prénom.
     *
     * @return Prénom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Met à jour le prénom.
     *
     * @param prenom Nouveau prénom.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Retourne la date de naissance.
     *
     * @return Date de naissance (Date).
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Met à jour la date de naissance.
     *
     * @param dateNaissance Nouvelle date de naissance.
     */
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * Retourne la date de décès, si elle existe.
     *
     * @return Date de décès, si elle existe (Date).
     */
    public Date getDateDeces() {
        return dateDeces;
    }

    /**
     * Met à jour la date de décès.
     *
     * @param dateDeces Nouvelle date de décès.
     */
    public void setDateDeces(Date dateDeces) {
        this.dateDeces = dateDeces;
    }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Auteur other = (Auteur) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
