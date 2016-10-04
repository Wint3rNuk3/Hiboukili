package model.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import model.classes.Edition;

public class ShoppingCartBean implements Serializable {
    
    HashMap<String, Edition> map; // remplacer ouvrage par Edition si besoin

    public ShoppingCartBean() {
        this.map = new HashMap();
    }
    
    public void create(String isbn) {
        create(isbn, +1);
//        Edition o = map.get(isbn);
//        o.stock--;
        map.get(isbn).stock--;
    }
    public void create(String isbn, int qty) {
        add(isbn, qty);
        map.get(isbn).stock -= qty;
        // need peut être une methode updateStock();
    }
    
    public void add(String isbn, int qty) {
        if(map.containsKey(isbn)) {
           Edition o = map.get(isbn);
//            o.setQty(o.getQty()+qty);
            o.change(qty);
            map.get(isbn).stock -= qty; //get stock
            if(o.getQty()<1){
                del(isbn);
            }
        } else {
            map.put(isbn, new Edition(isbn, qty));
            map.get(isbn).stock--;
        }
    }
    
    public void inc(String isbn) {
        add(isbn, +1);
        map.get(isbn).stock--;
    }
    public void dec(String isbn) {
        dec(isbn, 1);
        map.get(isbn).stock++;
    }
    public void dec( String isbn, int qty) {
        add( isbn, -qty);
        map.get(isbn).stock += qty;
    }
    public void del(String isbn) {
        map.get(isbn).qty = 0;                      // get ou set
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
}