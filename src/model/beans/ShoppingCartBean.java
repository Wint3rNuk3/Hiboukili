package model.beans;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import model.classes.Edition;

public class ShoppingCartBean implements Serializable {

    ConnexionBean bc;
    Map<String, Edition> map;

    public ShoppingCartBean() {
        this.map = new HashMap();
    }

    public ShoppingCartBean(ConnexionBean bc, Map<String, Edition> map) {
        this.map = map;
        this.bc = bc;
    }

    public void create(String isbn, Edition e) {
        create(isbn, e, +1);
    }

    public void create(String isbn, Edition e, int qty) {
        add(isbn, e, qty);
    }

//    public void add(String isbn, Edition e, int qty) {
//        if (map.containsKey(isbn)) {
//            e.change(qty);
//           if (e.getCartQty() < 1) {
//               del(isbn);
//            }
//        } else {
//            e.change(qty);
//            map.put(isbn, e);
//        }
//    }
    // faire la modification du stock à la commande
    // la methode add ne fonctionne pas correctement, à voir.
    public void add(String isbn, Edition e, int qty) {
        if (map.containsKey(isbn)) {
            map.get(isbn).change(qty);
            if (map.get(isbn).getCartQty() < 1) {
                del(isbn);
            }
        } else {
            e.change(qty);
            map.put(isbn, e);
        }
    }

    public void inc(String isbn, Edition e) {
        add(isbn, e, +1);
    }

    public void dec(String isbn, Edition e) {
        dec(isbn, e, 1);
    }

    public void dec(String isbn, Edition e, int qty) {
        add(isbn, e, -qty);
    }

    public void del(String isbn) {
        map.remove(isbn);
    }

    public Collection<Edition> list() {
        return map.values();
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public void clean() {
        map.clear();
    }

    public void save() {
    }

    public void load() {
    }

    public void checkout() {
        // validation du panier...
    }

    public void setBc(ConnexionBean bc) {
        this.bc = bc;
    }

    public Map<String, Edition> getMap() {
        return map;
    }

    public void setMap(Map<String, Edition> map) {
        this.map = map;
    }

    public String getCartPrice() {
        Float prixTotal = 0F;
        if (!(this.list().isEmpty())) {
            for (Edition e : this.list()) {
                if (e.getPrixHt() != null) {
                    e.initPrix();
                    prixTotal += (Float.parseFloat(e.getPrix())) * (e.getCartQty());
                }
            }
        }

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.FRENCH);
        otherSymbols.setDecimalSeparator('.');
        otherSymbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);
        df.setRoundingMode(RoundingMode.HALF_UP);

        //System.out.println(df.format(prixTotal));
        return df.format(prixTotal);
    }
}
