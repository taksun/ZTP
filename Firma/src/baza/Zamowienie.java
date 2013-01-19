/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Karolina
 */
public class Zamowienie implements Serializable {

    private int zamID;
    private Date data;
    private String status;
    private ArrayList<Produkt> produkty;
    private int klientID;
    private int fakID;
    private boolean cenawEuro;

    public Zamowienie(int zID, String s, ArrayList<Produkt> p, int k, boolean e) {
        zamID = zID;
        data = new Date();
        status = s;
        produkty = p;
        klientID = k;
        fakID = 0;
        cenawEuro = e;
    }

    public int getID() {
        return zamID;
    }

    public int getKlientID() {
        return klientID;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Produkt> getProdukty() {
        return produkty;
    }

    public int getFakID() {
        return fakID;
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

    public void setFakID(int fakID) {
        this.fakID = fakID;
    }

    public boolean isEuro() {
        return cenawEuro;
    }

    public void setCenawEuro(boolean cenawEuro) {
        this.cenawEuro = cenawEuro;
    }

    public Object[] toTable() {
        MyDB baza = MyDB.getInstance();
        String c = "Nie";
        if (cenawEuro) {
            c = "Tak";
        }
        Object[] tab = {zamID, data, status, baza.getKlientByID(klientID), c};
        return tab;
    }

    @Override
    public String toString() {
        return "Zamówienie nr: " + zamID;
    }
}
