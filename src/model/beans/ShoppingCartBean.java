package model.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import model.classes.Ouvrage;

public class ShoppingCartBean implements Serializable {
    
    HashMap<String, Ouvrage> map; // remplacer ouvrage par Edition si besoin
    Integer qty;

    public ShoppingCartBean() {
        this.map = new HashMap();
    }
    
    public void create(String isbn) {
        create(isbn, +1);
    }
    public void create(String isbn, int qty) {
        add(isbn, qty);
    }
    
    public void add(String isbn, int qty) {
        if(map.containsKey(isbn)) {
           Ouvrage i = map.get(isbn);
//            i.setQty(i.getQty()+qty);
            i.change(qty);
            if( i.getQty()<1)
                del( ref);
        } else {
            map.put(ref, new Item(ref, qty));
        }
    }
    
    public void inc( String ref) {
        add( ref, +1);
    }
    public void dec( String ref) {
        dec( ref, 1);
    }
    public void dec( String ref, int qty) {
        add( ref, -qty);
    }
    public void del( String ref) {
        map.remove(ref);
    }
    public Collection<Ouvrage> list() {
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
}