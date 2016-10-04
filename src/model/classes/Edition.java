package model.classes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Enregistre les différentes info. relatives à l'édition d'un ouvrage.
 *
 * @see Ouvrage
 */
public class Edition {

    private Long id;
    
    /**
     * Isbn : numero unique identifiant l'édition d'un ouvrage. (chaque édition
     * différente d'un même ouvrage aura un isbn différent) l'isbn est composé
     * de 10 chiffres (13 avec les tirets) jusqu'en 2006 et de 13 (17 avec les
     * tirets) chiffres à partir du 1er janvier 2007. (propriété typée String
     * car ne fera pas l'objet de calculs).
     */
    private String isbn;
    /**
     * Date de parution (typée Date).
     */
    private Date datePublication;
    /**
     * Prix hors taxes (typé float).
     */
    private Float prixHt;
    /**
     * String spécifiant le chemin d'accès à l'image de couverture.
     */
    private String couverture;
    /**
     * Titre de l'édition(au cas où il serait différent de celui de l'ouvrage).
     */
    private String titre;
    /**
     * Quantité physique disponible.
     */
    private Integer stock;

    /**
     * Statut de l'édition.
     *
     * @see StatutEdition
     * @see StatutEditionDAO
     */
    
    public int qty;
    
    
    private StatutEdition statut;
    /**
     * Ouvrage auquel se réfère l'édition.
     *
     * @see Ouvrage
     */
    private Ouvrage ouvrage;
    /**
     * Langue dans laquelle est publiée l'ouvrage
     *
     * @see Langue
     */
    private Langue langue;
    /**
     * Liste (s'il venait à y avoir plus d'une taxe) des taxes applicables à
     * l'édition. Utilisation d'une collection liste pour enregistrer l'ensemble
     * des taxes applicables.
     *
     * @see Taxe
     */
    private List<Taxe> taxes;
    
    //private Editeur editeur;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Edition() {
    }
    
    public Edition(String isbn, int qty){
        this.isbn = isbn;
        this.qty = qty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne l'ISBN.
     *
     * @return Numéro d'ISBN (String).
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Met à jour l'ISBN.
     *
     * @param isbn Nouveau numéro d'ISBN.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Retourne la date de publication.
     *
     * @return Date de publication (Date).
     */
    public Date getDatePublication() {
        return datePublication;
    }

    /**
     * Met à jour la date de publication.
     *
     * @param datePublication Nouvelle date de publication.
     */
    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    /**
     * Retourne le prix hors taxes.
     *
     * @return Prix hors taxes (float).
     */
    public Float getPrixHt() {
        return prixHt;
    }

    /**
     * Met à jour le prix hors taxes.
     *
     * @param prixHt Nouveau prix hors taxes.
     */
    public void setPrixHt(Float prixHt) {
        this.prixHt = prixHt;
    }

    /**
     * Retourne le chemin d'accès à l'image de couverture.
     *
     * @return Chemin d'accès à l'image de couverture (String).
     */
    public String getCouverture() {
        return couverture;
    }

    /**
     * Met à jour le chemin spécifiant le chemin d'accès à l'image de
     * couverture.
     *
     * @param couverture Nouveau chemin d'accès à l'image de couverture.
     */
    public void setCouverture(String couverture) {
        this.couverture = couverture;
    }

    /**
     * Retourne le titre.
     *
     * @return Titre de l'édition.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Met à jour le titre.
     *
     * @param titre Nouveau titre.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne le stock disponible.
     *
     * @return Stock disponible (int).
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Met à jour le stock.
     *
     * @param stock Nouveau stock.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void change(int qty) {
        this.qty+=qty;
    }
    
    
    
    
    /**
     * Retourne le statut de l'édition.
     *
     * @return Statut de l'édition.
     *
     * @see StatutEdition
     * @see StatutEditionDAO
     *
     */
    public StatutEdition getStatut() {
        return statut;
    }

    /**
     * Met à jour le statut de l'édition.
     *
     * @param statut Nouveau Statut d'édition.
     *
     * @see StatutEdition
     * @see StatutEditionDAO
     */
    public void setStatut(StatutEdition statut) {
        this.statut = statut;
    }

    /**
     * Retourne l'ouvrage auquel se réfère l'édition.
     *
     * @return Ouvrage de référence.
     *
     * @see Ouvrage
     *
     */
    public Ouvrage getOuvrage() {
        return ouvrage;
    }

    /**
     * Met à jour l'ouvrage auquel se réfère l'édition.
     *
     * @param ouvrage Nouvel ouvrage de référence.
     *
     * @see Ouvrage
     */
    public void setOuvrage(Ouvrage ouvrage) {
        this.ouvrage = ouvrage;

    }

    /**
     * Retourne la langue d'écriture de l'édition.
     *
     * @return Langue d'édition.
     *
     * @see Langue
     *
     */
    public Langue getLangue() {
        return langue;
    }

    /**
     * Met à jour la langue de l'édition.
     *
     * @param langue Nouvelle langue d'édition.
     *
     * @see Langue
     */
    public void setLangue(Langue langue) {
        this.langue = langue;
    }
//    
//    public Editeur getEditeur(){
//        return editeur;
//    }
//    
//    public void setEditeur(Editeur editeur){
//        this.editeur = editeur;
//    }
    /**
     * Retourne la/les taxe(s).
     *
     * @return Montant de la/des Taxe(s).
     *
     * @see Taxe
     *
     */

    /**
     * Met à jour la/les taxe(s).
     *Utilisation d'une collection pour enregistrer toutes les taxes affectées
     * à une édition.
     * @param taxes Nouveau montant de taxe.
     *
     * @see Taxe
     */

    
     public List<Taxe> getTaxes() {
        return taxes;
    }

    public void setTaxes(List<Taxe> taxes) {
        this.taxes = taxes;
    }

    public void addTaxe(Taxe taxe) {
        if (this.taxes == null) {
            this.taxes = new ArrayList<>();
        }

        this.taxes.add(taxe);
    }

   
    
    
    @Override
    public String toString() {
        return "Edition{"
                + "isbn=" + isbn
                + ", datePublication=" + datePublication
                + ", prixHt=" + prixHt
                + ", couverture=" + couverture
                + ", titre=" + titre
                + ", stock=" + stock
                + ", statut=" + statut
                + ", ouvrage=" + ouvrage
                + ", langue=" + langue
                + ", taxes=" + taxes + '}';
    }

}
