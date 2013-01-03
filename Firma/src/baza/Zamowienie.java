/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Karolina
 */
public class Zamowienie {
   
    private int zamID;
    private Date data;
    private String status;// nie wiem czy tak, bo w sumie chyba mozna inta i zrobic 0-niezrealizowane, 1-czeka,2-zrealizowane.. dokładnie nie wiem
    private ArrayList<Produkt> produkty = new ArrayList<>();
    private int klientID;

    public Zamowienie(int zID, String s, ArrayList<Produkt> p, int k) {
        zamID = zID;
        data = new Date();
        status = s;
        produkty  = p;
        klientID = k;
    }
    
    public int getID() {
        return zamID;
    }
    
    public void setStatus(String s) {
        status = s;
    }
}
