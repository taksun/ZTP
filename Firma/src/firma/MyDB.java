/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

import java.util.ArrayList;

/**
 *
 * @author taksun
 */
public class MyDB {
    private static MyDB singleton;
    
    private ArrayList<Produkt> listaProdukty = new ArrayList<>();
    
    protected MyDB() {
        
    }
    
    public static MyDB getInstance() {
        if (singleton == null) {
            singleton = new MyDB();
        }
        return singleton;
    }
    
    public void dodajProdukt(Produkt p) {
        listaProdukty.add(p);
    }
    
    public void usunProdukt(Produkt p) {
        listaProdukty.remove(p);
    }
    
    public void edytujProdukt(Produkt oldP, Produkt newP) {
        listaProdukty.remove(oldP);
        listaProdukty.add(newP);
    }
    
    public ArrayList<Produkt> getProdukty() {
        return listaProdukty;
    }
}
