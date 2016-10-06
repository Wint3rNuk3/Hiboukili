package model.classes;

import java.io.Serializable;
import java.sql.Date;

/**
 * Enregistre la date d'une transaction.
 */
public class Transaction implements Serializable{

    /**
     * Séquentiel de la table Transaction généré automatiquement par la BDD.
     */
    private Long id;
    /**
     * Date de la transaction.
     */
    private Date dateTransaction;

    /**
     * Constructeur de la classe; accès sans utiliser de paramètres.
     */
    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    @Override
    public String toString() {
        return "Transaction{" + "id=" + id + ", dateTransaction=" + dateTransaction + '}';
    }

}
