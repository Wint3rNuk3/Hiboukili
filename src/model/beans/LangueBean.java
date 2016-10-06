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
import model.classes.Langue;

public class LangueBean {

    private static final Logger LOG = Logger.getLogger(LangueBean.class.getName());

    private static final String SQL_FIND_ALL = "SELECT"
            + " idLangue, libelle, code"
            + " FROM Langue";

    private static final String SQL_FIND_BY_ID = "SELECT"
            + " idLangue, libelle, code"
            + " FROM Langue"
            + " WHERE idLangue = ?";

    public List<Langue> findAll(ConnexionBean bc) {
        List<Langue> list = new ArrayList<>();

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {

            PreparedStatement ps = c.prepareStatement(SQL_FIND_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Langue item = new Langue();

                item.setId(rs.getLong("idLangue"));
                item.setLibelle(rs.getString("libelle"));

                list.add(item);
            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public Langue findById(ConnexionBean bc, Long id) {
        Langue item = null;

        // le nom de méthode commence par une majuscule,
        // mais ce n'est pas de mon ressort.
        DataSource ds = bc.MaConnexion();

        try (Connection c = ds.getConnection()) {
            PreparedStatement ps = c.prepareStatement(SQL_FIND_BY_ID);

            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                item = new Langue();
                item.setId(rs.getLong("idLangue"));
                item.setLibelle(rs.getString("Libelle"));
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }

        return item;
    }
}
