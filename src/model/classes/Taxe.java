/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.classes;

import java.io.Serializable;

/**
 * Enregistre les différentes info. relatives aà la/aux taxe(s) appliquée(s)
 * lors de la vente d'un ouvrage à partir de son prix hors taxes.
 *
 */
public class Taxe implements Serializable{

        /**
     * Séquentiel de la table Taxe généré automatiquement par la
     * BDD.
     */
    private Long id;
    /**
     * Libellé de la taxe.
     */
    private String libelle;
    /**
     * Valeur appliquée en %age.
     */
    private float valeur;
    
    
    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Taxe() {
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

    public Float getValeur() {
        return valeur;
    }

    public void setValeur(Float valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Taxe{" + "id=" + id + ", libelle=" + libelle + ", valeur=" + valeur + '}';
    }

}
