package model.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import model.classes.ShoppingCart;

public class ShoppingCartBean implements Serializable {
    
    HashMap<String, Ouvrage> map; // remplacer ouvrage par Edition si besoin

    public ShoppingCartBean() {
        this.map= new HashMap();
    }
    public void create( String ref) {
        create( ref, +1);
    }
    public void create( String ref, int qty) {
        add( ref, qty);
    }
    public void add( String ref, int qty) {
        if( map.containsKey(ref)) {
            Item i= map.get(ref);
//            i.setQty( i.getQty()+qty);
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
    public Collection<Item> list() {
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
public class shoppingCartBean {

}