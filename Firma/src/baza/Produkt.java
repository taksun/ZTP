/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author taksun
 */
public class Produkt {
    private int produktID;
    private int kategoriaID;
    private float cena;
    private float cena_euro;
    private int ilosc;
    private String nazwa;
    private float vat;
    
    public Produkt(int pID, int kID, float c, float ce, int i, String n, float v) {
        produktID = pID;
        kategoriaID = kID;
        cena = c;
        cena_euro = ce;
        ilosc = i;
        nazwa = n;
        vat = v;
    }
    
    public int getID() {
        return produktID;
    }
    
    public void setNazwa(String n) {
        nazwa = n;
    }
}
