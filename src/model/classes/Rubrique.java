package model.classes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Enregistre les différentes info. relatives à une rubrique.
 */
public class Rubrique {

    /**
     * Séquentiel de la table Rubrique généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Nom de la rubrique.
     */
    private String libelle;
    /**
     * Date d'entrée en vigueur de la rubrique.
     */
    private Date dateDebut;
    /**
     * Date de fin de la rubrique.
     */
    private Date dateFin;
    /**
     * Info. relatives à la rubrique.
     */
    private String commentaire;
    
    
    private List<Ouvrage> ouvrages;
    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Rubrique() {
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public List<Ouvrage> getOuvrage(){
        
        return ouvrages;
    }
    
    public void setOuvrage(List<Ouvrage> ouvrages){
        
        this.ouvrages = ouvrages;
    }
    
    public void addOuvrage(Ouvrage ouvrage){
        if(this.ouvrages == null){
            this.ouvrages = new ArrayList<>();
        }
        this.ouvrages.add(ouvrage);
    }
      


    @Override
    public String toString() {
        return libelle;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final Rubrique other = (Rubrique) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
