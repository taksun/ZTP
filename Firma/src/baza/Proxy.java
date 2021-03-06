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
    public void addZamowienie(int klientID, ArrayList<Produkt> produkty, boolean e) {

        for (Produkt p : produkty) {
            Produkt prod = baza.getProduktByID(p.getID());
            prod.setIlosc(prod.getIlosc() - p.getIlosc());
        }

        baza.addZamowienie(klientID, produkty, e);
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

        Zamowienie z = baza.getZamowienie(id);

        if (!z.getStatus().equals("Zrealizowane")) {
            for (Produkt item : z.getProdukty()) {
                baza.setProduktIloscByID(item.getID(), baza.getProduktIloscByID(item.getID()) + item.getIlosc());
            }
        }

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

    @Override
    public void editZamowienie(int id, int klientID, ArrayList<Produkt> produkty, boolean e) {

        Zamowienie z = baza.getZamowienieByID(id);

        for (Produkt item : z.getProdukty()) {
            baza.setProduktIloscByID(item.getID(), baza.getProduktIloscByID(item.getID()) + item.getIlosc());
        }

        for (Produkt p : produkty) {
            Produkt prod = baza.getProduktByID(p.getID());
            prod.setIlosc(prod.getIlosc() - p.getIlosc());
        }

        baza.editZamowienie(id, klientID, produkty, e);
    }

    @Override
    public Klient getKlient(int nr) {
        return baza.getKlient(nr);
    }
}
