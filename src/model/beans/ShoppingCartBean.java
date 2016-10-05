package model.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import model.classes.Edition;

public class ShoppingCartBean implements Serializable {

    BeanConnexion bc;
    Map<String, Edition> map;

    public ShoppingCartBean() {
        this.map = new HashMap();
        //this.mapBean = new HashMap();
    }

    public ShoppingCartBean(BeanConnexion bc, Map<String, Edition> map) {
        this.map = map;
        this.bc = bc;
        //this.mapBean = new HashMap();
    }

    public void create(String isbn, Edition e) {
        //System.out.println(e.toString());
        create(isbn, e, +1);
    }

    public void create(String isbn, Edition e, int qty) {
        add(isbn, e, qty);
        //map.get(isbn).setStock(map.get(isbn).getStock() - qty);
    }

    public void add(String isbn, Edition e, int qty) {
        if (map.containsKey(isbn)) {
            Edition ed = map.get(isbn);
            ed.change(qty);
            if (ed.getCartQty() < 1) {
                del(isbn);
            }
        } else {
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
        //map.get(isbn).setStock(map.get(isbn).getStock() + qty);
    }

    public void del(String isbn) {
        //map.get(isbn).setStock(0);
        map.remove(isbn);
    }

    public Collection<Edition> list() {
        return map.values();
    }
    
    public String prix(String isbn, Edition e){
        double prix = e.getCartQty() * (e.getPrixHt() + ((e.getTaxes().iterator().next().getValeur() /100) * e.getPrixHt()));
        return String.valueOf(prix);
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

    public void setBc(BeanConnexion bc) {
        this.bc = bc;
    }

    public void setMap(Map<String, Edition> map) {
        this.map = map;
    }

}
