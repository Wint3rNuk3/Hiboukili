package model.classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les différentes info. relatives à un utlisateur (client ou
 * employé).
 *
 */
public class Utilisateur implements Serializable{

    /**
     * Séquentiel de la table Ouvrage généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom de l'utilisateur.
     */
    private String nom;
    /**
     * Prenom.
     */
    private String prenom;
    /**
     * Mot de passe utilisé pour se connecter.
     */
    private String motDePasse;
    /**
     * E-mail.
     */
    private String email;
    /**
     * N° de téléphone.
     */
    private String telephone;
    /**
     * Date de naissance.
     */
    private Date dateNaissance;

    /**
     * Distingue le client de l'employé.
     *
     * @see Role
     */
    private List<Role> roles;
    /**
     * Statut de l'utilisateur.
     *
     * @see StatutUtilisateur
     * @see StatutUtilisateurDAO
     */
    private StatutUtilisateur statut;
    /**
     * Enregistre dans une collection l'historique des adresses de livraison
     * utilisées par le client lors de ses commandes.
     */
    private List<Adresse> dernieresLivraisons;
    /**
     * Enregistre dans une collection l'historique des adresses de facturation
     * utilisées par le client lors de ses commandes.
     */
    private List<Adresse> dernieresFacturations;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Utilisateur() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public StatutUtilisateur getStatut() {
        return statut;
    }

    public void setStatut(StatutUtilisateur statut) {
        this.statut = statut;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }

        this.roles.add(role);
    }

    public List<Adresse> getDernieresLivraisons() {
        return dernieresLivraisons;
    }

    public void setDernieresLivraisons(List<Adresse> adresses) {
        this.dernieresLivraisons = adresses;
    }

    public void addDerniereLivraison(Adresse adresse) {
        if (this.dernieresLivraisons == null) {
            this.dernieresLivraisons = new ArrayList<>();
        }

        this.dernieresLivraisons.add(adresse);
    }

    public List<Adresse> getDernieresFacturations() {
        return dernieresFacturations;
    }

    public void setDernieresFacturations(List<Adresse> adresses) {
        this.dernieresFacturations = adresses;
    }

    public void addDerniereFacturation(Adresse adresse) {
        if (this.dernieresFacturations == null) {
            this.dernieresFacturations = new ArrayList<>();
        }

        this.dernieresFacturations.add(adresse);
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
