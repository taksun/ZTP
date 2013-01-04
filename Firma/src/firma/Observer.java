/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

import baza.MyDB;
import baza.Produkt;
import java.util.ArrayList;

/**
 *
 * @author taksun
 */
public class Observer {
    Ustawienia ust;
    
    public Observer(Ustawienia u) {
        ust = u;
    }
    
    public void update() {
        MyDB baza = MyDB.getInstance();
        
        ArrayList<Produkt> lista = baza.getProduktyList();
        
        for (Produkt p : lista) {
            float cena = p.getCena()*ust.getKurs()*100;
            cena = Math.round(cena);
            cena = cena / 100;
            p.setCenaEuro(cena);
        }
    }
}
