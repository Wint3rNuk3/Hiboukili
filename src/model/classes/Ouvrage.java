package model.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les différentes info. relatives à un ouvrage.
 *
 */
public class Ouvrage {

    /**
     * Séquentiel de la table Ouvrage généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Titre de l'ouvrage.
     */
    private String titre;
    /**
     * Sous-titre, s'il en existe un.
     */
    private String sousTitre;
    /**
     * Résumé.
     */
    private String resume;
    /**
     * Info. sr l'auteur.
     *
     * @see Auteur
     */
    private Auteur auteur;

    /**
     * Liste du/des co-auteur(s), s'il y a lieu, enregistrée dans une
     * collection.
     */
    private List<Auteur> coAuteurs;

//    private Langue langue;
    /**
     * Liste des thèmes abordés, enregistrée dans une collection.
     *
     * @see Theme
     */
    private List<Theme> themes;
    /**
     * Liste des rubriques auxquelles est affecté l'ouvrage, enregistrée dans
     * une collection.
     *
     * @see Rubrique
     */
    private List<Rubrique> rubriques;
    /**
     * Liste des genres auxquels appartient l'ouvrage, enregistréee dans une col
     * lection.
     *
     * @see Genre
     */
    private List<Genre> genres;
    /**
     * Liste des mots-clefs relatifs à l'ouvrage, enregistrée dans une
     * collection.
     *
     * @see Tag
     */
    private List<Tag> tags;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Ouvrage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSousTitre() {
        return sousTitre;
    }

    public void setSousTitre(String sousTitre) {
        this.sousTitre = sousTitre;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public List<Auteur> getCoAuteurs() {
        return coAuteurs;
    }

    public void setCoAuteurs(List<Auteur> coAuteurs) {
        this.coAuteurs = coAuteurs;
    }

    public void addCoAuteur(Auteur coAuteur) {
        if (this.coAuteurs == null) {
            this.coAuteurs = new ArrayList<>();
        }

        this.coAuteurs.add(coAuteur);
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public void addTheme(Theme theme) {
        if (this.themes == null) {
            this.themes = new ArrayList<>();
        }

        this.themes.add(theme);
    }

//    public Langue getLangue(){
//        return langue;
//    }
//    
//    public void setLangue(Langue langue){
//        this.langue = langue;
//    }
    public List<Rubrique> getRubriques() {
        return rubriques;
    }

    public void setRubriques(List<Rubrique> rubriques) {
        this.rubriques = rubriques;
    }

    public void addRubrique(Rubrique rubrique) {
        if (this.rubriques == null) {
            this.rubriques = new ArrayList<>();
        }

        this.rubriques.add(rubrique);
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        if (this.genres == null) {
            this.genres = new ArrayList<>();
        }

        this.genres.add(genre);
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if (this.tags == null) {
            this.tags = new ArrayList<>();
        }

        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return "Ouvrage{" + "id=" + id + ", titre=" + titre + ", sousTitre=" + sousTitre + ", resume=" + resume + ", auteur=" + auteur + ", coAuteurs=" + coAuteurs + ", themes=" + themes + ", rubriques=" + rubriques + ", genres=" + genres + ", tags=" + tags + '}';
    }

}
