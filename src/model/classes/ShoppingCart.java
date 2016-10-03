package model.classes;

import java.util.HashMap;

public class ShoppingCart {
    private HashMap<String, Ouvrage> map;

    public ShoppingCart() {
        this.map = new HashMap();
    }
    
    public ShoppingCart(HashMap<String, Ouvrage> map) {
        this.map = map;
    }

    public HashMap<String, Ouvrage> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Ouvrage> map) {
        this.map = map;
    }
    
}