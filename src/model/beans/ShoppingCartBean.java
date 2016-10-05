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
        if (map.size() > 1) {
            //System.out.println(e);
            e.change(qty);
            //map.get(isbn).setStock(map.get(isbn).getStock() - qty);
            map.put(isbn, e);
            System.out.println("la map si le panier contient un element : " + map);
            if (e.getCartQty() < 1) {
                del(isbn, e);
            }
        } else {
            //System.out.println(e);
            map.put(isbn, e);
            System.out.println("la map si le panier contient 0 element : " + map);
            //map.get(isbn).setStock(map.get(isbn).getStock() - 1);
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
        map.get(isbn).setStock(map.get(isbn).getStock() + qty);
    }

    public void del(String isbn, Edition e) {
        map.get(isbn).setStock(0);
        map.remove(isbn);
    }

    public Collection<Edition> list() {
        return map.values();
    }

    public Set keyList() {
        return map.keySet();
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
