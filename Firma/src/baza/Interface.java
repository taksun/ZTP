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
public interface Interface {
    public void addZamowienie(int klientID, ArrayList<Produkt> produkty);
    public Object[] getLastZamowienie();
    public int getKlientID(int nr);
    public Object[][] getZamowienia();
    public Zamowienie getZamowienie(int id);
    public void removeZamowienie(int id);
    public int getKlientRowByID(int id);
    public Produkt getProduktByID(int id);
    public void editZamowienie(int id, int klientID, ArrayList<Produkt> produkty);
}
