/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.Serializable;

/**
 *
 * @author taksun
 */
public class Produkt implements Serializable {

    private int produktID;
    private int kategoriaID;
    private float cena;
    private float cena_euro;
    private int ilosc;
    private String nazwa;
    private float vat;
    
    public Produkt(Produkt p) {
        produktID = p.getID();
        kategoriaID = p.getKategoria();
        cena = p.getCena();
        cena_euro = p.getCena_euro();
        ilosc = p.getIlosc();
        nazwa = p.getNazwa();
        vat = p.getVAT();
    }

    public Produkt(int pID, String n, int kID, float c, float ce, int i, float v) {
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
    
    public String getNazwa() {
        return nazwa;
    }

    public float getCena() {
        return cena;
    }
    
    public void setCena(float c) {
        cena = c;
    }

    public void setCenaEuro(float c) {
        cena_euro = c;
    }
    
    public int getIlosc() {
        return ilosc;
    }
    
    public void setIlosc(int il) {
        ilosc = il;
    }
    
    public int getKategoria() {
        return kategoriaID;
    }
    
    public void setKategoria(int k) {
        kategoriaID = k;
    }
    
    public float getVAT() {
        return vat;
    }
    
    public void setVAT(float v) {
        vat = v;
    }

    public float getCena_euro() {
        return cena_euro;
    }

    public Object[] toTable() {
        MyDB baza = MyDB.getInstance();
        float cenaV = cena * vat + cena;
        cenaV = cenaV * 100;
        cenaV = Math.round(cenaV);
        cenaV = cenaV / 100;
        float cenaEV = cena_euro * vat + cena_euro;
        cenaEV = cenaEV * 100;
        cenaEV = Math.round(cenaEV);
        cenaEV = cenaEV / 100;
        Object[] tab = {produktID, nazwa, baza.getKategoriaByID(kategoriaID).getNazwa(), ilosc, cena, cena_euro, Integer.toString((int) (vat * 100)) + "%", cenaV, cenaEV};
        return tab;
    }
    
    @Override
    public String toString() {
        return Integer.toString(produktID) + ": " +nazwa;
    }
}
