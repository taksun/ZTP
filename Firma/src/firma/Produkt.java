/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

/**
 *
 * @author taksun
 */
public class Produkt {
    private int produktID;
    private float cena;
    private float cena_euro;
    private int ilosc;
    private String nazwa;
    private float vat;
    
    public Produkt(int pID, float c, float ce, int i, String n, float v) {
        produktID = pID;
        cena = c;
        cena_euro = ce;
        ilosc = i;
        nazwa = n;
        vat = v;
    }
    
    public String getOpis() {
        return "ID produktu: " + produktID + "\n" + nazwa + "\nCena: " + cena + "\nCena w euro: " + cena_euro + "\nIlość: " + ilosc + "\nStawka VAT: " + vat + "\n\n";
    }
}
