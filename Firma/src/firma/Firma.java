/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

import java.util.ArrayList;

/**
 *
 * @author Yatsek
 */
public class Firma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //test MyDB
        
        MyDB baza = MyDB.getInstance();
        
        Produkt p = new Produkt(1, 2.0F, 8.0F, 10, "Laptop Acer", 0.23F);
        
        baza.dodajProdukt(p);
        
        p = new Produkt(2, 4.0F, 16.0F, 10, "Laptop HP", 0.23F);
        
        baza.dodajProdukt(p);
        
        ArrayList<Produkt> lista = baza.getProdukty();
        
        for (Produkt item : lista) {
            System.out.print(item.getOpis());
        }
    }
}
