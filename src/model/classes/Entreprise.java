package model.classes;

import java.io.Serializable;

/**
 * Enregistre les info. obligatoires relatives à l'entreprise.
 */
public class Entreprise implements Serializable{

    /**
     * Séquentiel de la table Entreprise généré automatiquement par la BDD.
     */
    private Long id;

    /**
     * Statut de lentreprise
     *
     * @see StatutEntreprise
     * @see StatutEntrepriseDAO
     */
    private StatutEntreprise statut;
    /**
     * N° de Siren (9 chiffres + 2 espaces)
     */
    private String siren;
    /**
     * N° de RCS (rcs + 14 chiffres et 4 espaces)
     */
    private String rcs;
    /**
     * Forme juridique de l'entreprise
     */
    private String formeJuridique;
    /**
     * Capital de l'entreprise
     */
    private Double capital;
    /**
     * N° de TVA de l'entreprise FR + 11 chiffres
     */
    private String numTVA;

    public Entreprise() {
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
     * Retourne le Statut de l'entreprise.
     *
     * @return Statut d'entreprise.
     *
     * @see StatutEntreprise
     * @see StatutEntrepriseDA
     */
    public StatutEntreprise getStatut() {
        return statut;
    }

    /**
     * Met à jour Statut de l'entreprise.
     *
     * @param statut Nouveau statut d'entreprise.
     */
    public void setStatut(StatutEntreprise statut) {
        this.statut = statut;
    }

    /**
     * Retourne le N° de siren.
     *
     * @return N° de siren (String).
     */
    public String getSiren() {
        return siren;
    }

    /**
     * Met à jour le siren.
     *
     * @param siren Nouveau siren.
     */
    public void setSiren(String siren) {
        this.siren = siren;
    }

    /**
     * Retourne le N° de rcs.
     *
     * @return N° de rcs (String).
     */
    public String getRcs() {
        return rcs;
    }

    /**
     * Met à jour le rcs.
     *
     * @param rcs Nouveau rcs.
     */
    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    /**
     * Retourne la forme juridique.
     *
     * @return Forme juridique (String).
     */
    public String getFormeJuridique() {
        return formeJuridique;
    }

    /**
     * Met à jour la forme juridique.
     *
     * @param formeJuridique Nouvelle forme juridique.
     */
    public void setFormeJuridique(String formeJuridique) {
        this.formeJuridique = formeJuridique;
    }

    /**
     * Retourne le capital.
     *
     * @return Capital (Double).
     */
    public Double getCapital() {
        return capital;
    }

    /**
     * Met à jour le Capital.
     *
     * @param capital Nouveau capital.
     */
    public void setCapital(Double capital) {
        this.capital = capital;
    }

    /**
     * Retourne le N° de TVA.
     *
     * @return N° de TVA (String).
     */
    public String getNumTVA() {
        return numTVA;
    }

    /**
     * Met à jour le N° de TVA.
     *
     * @param capital Nouveau N° de TVA.
     */
    public void setNumTVA(String numTVA) {
        this.numTVA = numTVA;
    }

}
