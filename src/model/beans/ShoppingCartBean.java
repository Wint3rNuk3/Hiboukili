package model.beans;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import model.classes.Edition;

public class ShoppingCartBean implements Serializable {

    BeanConnexion bc;
    HashMap<String, Edition> map;
    HashMap<String, EditionBean> mapBean;

    public ShoppingCartBean() {
        this.map = new HashMap();
        this.mapBean = null;
    }

    public ShoppingCartBean(BeanConnexion bc, HashMap<String, Edition> arr, HashMap<String, EditionBean> map) {
        this.map = arr;
        this.bc = bc;
        this.mapBean = map;
    }

    public void create(String isbn) {
        create(isbn, +1);
    }

    public void create(String isbn, int qty) {
        add(isbn, qty);
        //map.get(isbn).setStock(map.get(isbn).getStock() - qty);
    }

    public void add(String isbn, int qty) {
        if (map.size() > 1) {
            System.out.println(map);
            Edition e = map.get(isbn);
            e.change(qty);
            // reprendre ici
            map.get(isbn).setStock(map.get(isbn).getStock() - qty);
            if (e.getCartQty() < 1) {
                del(isbn);
            }
        } else {
            map.put(isbn, new Edition());
            //map.get(isbn).setStock(map.get(isbn).getStock() - 1);
        }
    }

    public void inc(String isbn) {
        add(isbn, +1);
    }

    public void dec(String isbn) {
        dec(isbn, 1);
    }

    public void dec(String isbn, int qty) {
        add(isbn, -qty);
        map.get(isbn).setStock(map.get(isbn).getStock() + qty);
    }

    public void del(String isbn) {
        map.get(isbn).setStock(0);
        map.remove(isbn);
    }

    public Collection<EditionBean> list() {
        System.out.println(map.values());

        if (mapBean == null) {
            return mapBean.values();
        } else {
            Iterator<Edition> it = map.values().iterator();
            int i = 0;
            while (it.hasNext() && i < map.values().size()) {
                mapBean.put(this.keyList().toArray()[i].toString(), new EditionBean());
                i++;
            }
            return mapBean.values();
        }

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
}
