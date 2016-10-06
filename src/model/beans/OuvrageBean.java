package model.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.classes.Auteur;
import model.classes.Genre;
import model.classes.Ouvrage;
import model.classes.Rubrique;
import model.classes.Tag;
import model.classes.Theme;

public class OuvrageBean {
    
    private static final Logger LOG = Logger.getLogger(OuvrageBean.class.getName());
    
    private static final String SQL_FIND_ALL = "SELECT"
            + " idOuvrage, idAuteur, titre, sous_titre, resume"
            + " FROM Ouvrage ";
    
    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idOuvrage, idAuteur, titre, sous_titre, resume"
            + " FROM Ouvrage"
            + " WHERE idOuvrage = ?";
    
    public List<Ouvrage> findAll(ConnexionBean bc) {
        List<Ouvrage> ouvrages = new ArrayList<>();
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            
            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Ouvrage ouvrage = new Ouvrage();
                Long idOuvrage = rs.getLong("idOuvrage");
                Long idAuteur = rs.getLong("idAuteur");
                
                ouvrage.setId(idOuvrage);
                ouvrage.setTitre(rs.getString("titre"));
                ouvrage.setSousTitre(rs.getString("sous_titre"));
                ouvrage.setResume(rs.getString("resume"));
                
                // recuperer les genres.
                List<Genre> genres = new GenreBean().findByOuvrage(bc, idOuvrage);
                ouvrage.setGenres(genres);
                
                // recuperer les rubriques.
                List<Rubrique> rubriques = new RubriqueBean().findByOuvrage(bc, idOuvrage);
                ouvrage.setRubriques(rubriques);
                
                // recuperer les tags.
                List<Tag> tags = new TagBean().findByOuvrage(bc, idOuvrage);
                ouvrage.setTags(tags);
                
                // recuperer les themes.
                List<Theme> themes = new ThemeBean().findByOuvrage(bc, idOuvrage);
                ouvrage.setThemes(themes);
                
                // recuperer l'auteur.
                Auteur auteur = new AuteurBean().findById(bc, idAuteur);
                ouvrage.setAuteur(auteur);
                
                // recuperer les co-auteurs.
                List<Auteur> coAuteurs = new AuteurBean().findCoAuteursByOuvrage(bc, idOuvrage);
                ouvrage.setCoAuteurs(coAuteurs);
                
                ouvrages.add(ouvrage);
            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return ouvrages;

    }
    
    public Ouvrage findById(ConnexionBean bc, Long id) {
        Ouvrage item = null;
        
        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();
        
        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);
            
            ps.setLong(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                
                item = new Ouvrage();
                Long idOuvrage = rs.getLong("idOuvrage");
                Long idAuteur = rs.getLong("idAuteur");

                item.setId(idOuvrage);
                item.setTitre(rs.getString("titre"));
                item.setSousTitre(rs.getString("sous_titre"));
                item.setResume(rs.getString("resume"));

                // recuperer les genres.
                List<Genre> genres = new GenreBean().findByOuvrage(bc, idOuvrage);
                item.setGenres(genres);

                // recuperer les rubriques.
                List<Rubrique> rubriques = new RubriqueBean().findByOuvrage(bc, idOuvrage);
                item.setRubriques(rubriques);

                // recuperer les tags.
                List<Tag> tags = new TagBean().findByOuvrage(bc, idOuvrage);
                item.setTags(tags);

                // recuperer les themes.
                List<Theme> themes = new ThemeBean().findByOuvrage(bc, idOuvrage);
                item.setThemes(themes);

                // recuperer l'auteur.
                Auteur auteur = new AuteurBean().findById(bc, idAuteur);
                item.setAuteur(auteur);

                // recuperer les co-auteurs.
                List<Auteur> coAuteurs = new AuteurBean().findCoAuteursByOuvrage(bc, idOuvrage);
                item.setCoAuteurs(coAuteurs);

            }
            
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item; 
    }

}