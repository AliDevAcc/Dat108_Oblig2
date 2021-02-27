package Oblig2;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    
    private static  List<CartItem> items = new ArrayList<>();
    private static int id;
    
    public void addItem(String item) {
        items.add(new CartItem(item, id++));
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public static void removeItem(int id) {
    	
    	items.remove(new CartItem(null, id));
    }
    
    
}

