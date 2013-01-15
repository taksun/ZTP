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
    private String status;
    private ArrayList<Produkt> produkty;
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
    
    public int getKlientID() {
        return klientID;
    }
    
    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }
    
    public void setProdukty(ArrayList<Produkt> p) {
        produkty = p;
    }
    
    public void setKlientID(int id) {
        klientID = id;
    }
    
    public void setStatus(String s) {
        status = s;
    }
    
    public Object[] toTable() {
        Object[] tab = {zamID, data, status, klientID};
        return tab;
    }
}
