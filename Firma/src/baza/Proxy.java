/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.util.ArrayList;

/**
 *
 * @author taksun
 */
public class Proxy implements Interface {
    private MyDB baza;
    
    public Proxy() {
        baza = MyDB.getInstance();
    }

    @Override
    public void addZamowienie(int klientID, ArrayList<Produkt> produkty) {
        baza.addZamowienie(klientID, produkty);
    }

    @Override
    public Object[] getLastZamowienie() {
        return baza.getLastZamowienie();
    }

    @Override
    public int getKlientID(int nr) {
        return baza.getKlientID(nr);
    }

    @Override
    public Object[][] getZamowienia() {
        return baza.getZamowienia();
    }

    @Override
    public Zamowienie getZamowienie(int id) {
        return baza.getZamowienie(id);
    }

    @Override
    public void removeZamowienie(int id) {
        baza.removeZamowienie(id);
    }

    @Override
    public int getKlientRowByID(int id) {
        return baza.getKlientRowByID(id);
    }

    @Override
    public Produkt getProduktByID(int id) {
        return baza.getProduktByID(id);
    }
}
