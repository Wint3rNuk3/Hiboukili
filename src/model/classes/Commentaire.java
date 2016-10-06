package model.classes;

import java.io.Serializable;

/**
 * Enregistre les commentaires rédigés par les Clients à propose d'une édition
 * d'un ouvrage.
 */
public class Commentaire implements Serializable{

    /**
     * Séquentiel de la table Commentaire généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Edition commentée.
     */
    private String titre;
    /**
     * Commentaire rédigé par le client.
     */
    private String contenu;
    /**
     * Note (entier typé int) attribuée par le client à l'édition
     */
    private int note;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Commentaire() {
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
     * @param id Nouvel id du commentaire.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le titre de l'édition commentée.
     *
     * @return Titre de l'édition commentée.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Met à jour le titre de l'édition commentée.
     *
     * @param titre Nouveau titre de l'édition.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne le contenu du commentaire.
     *
     * @return Commentaire client.
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Met à jour le commentaire.
     *
     * @param contenu Ajoute/modifie le commentaire d'un client.
     */
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    /**
     * Retourne la note laissée par un client sur un ouvrage.
     *
     * @return Note Utilisateur (int).
     */
    public int getNote() {
        return note;
    }

    /**
     * Met à jour la note de l'édition laissée par le client.
     *
     * @param note Modifie la note laissée par un Utilisateur.
     */
    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", note=" + note + '}';
    }

}
