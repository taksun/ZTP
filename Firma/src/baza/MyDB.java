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
public class MyDB implements Interface {

    private static MyDB singleton;
    
    private int nextKategoriaID = 1;
    private int nextProduktID = 1;
    private int nextKlientID = 1;
    private int nextZamowienieID = 1;
    
    private ArrayList<Produkt> listaProdukty = new ArrayList<>();
    private ArrayList<Kategoria> listaKategorie = new ArrayList<>();
    private ArrayList<Klient> listaKlienci = new ArrayList<>();
    private ArrayList<Zamowienie> listaZamowienia = new ArrayList<>();

    private MyDB() {
    }

    public static MyDB getInstance() {
        if (singleton == null) {
            singleton = new MyDB();
        }
        return singleton;
    }

    public void addProdukt(String nazwa, int kategoria, int ilosc, float cena, float cena_euro, float vat) {
        Produkt p = new Produkt(nextProduktID, nazwa, kategoria, cena, cena_euro, ilosc, vat);
        nextProduktID++;
        listaProdukty.add(p);
    }

    public void removeProdukt(int id) {
        listaProdukty.remove(id);
    }
    
    public int getProduktIlosc(int id) {
        return listaProdukty.get(id).getIlosc();
    }
    
    public void setProduktIlosc(int id, int ilosc) {
        listaProdukty.get(id).setIlosc(ilosc);
    }
    
    public Object[] getLastProdukt() {
        return listaProdukty.get(listaProdukty.size()-1).toTable();
    }

    public Produkt getProdukt(int id) {
        return listaProdukty.get(id);
    }
    
    public Object[][] getProdukty() {
        Object [][] data = new Object[listaProdukty.size()][6];
        
        for (int i=0; i<listaProdukty.size(); i++) {
            data[i] = listaProdukty.get(i).toTable();
        } 
        
        return data;
    }
    
    public ArrayList<Produkt> getProduktyList() {        
        return listaProdukty;
    }

    public void addKategoria(String nazwa) {
        int kategoriaID = nextKategoriaID;
        nextKategoriaID++;
        listaKategorie.add(new Kategoria(kategoriaID, nazwa));
    }

    public void removeKategoria(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID() == id) {
                listaKategorie.remove(k);

                break;
            }
        }
    }

    public void editKategoria(int id, String nazwa) {
        for (Kategoria k : listaKategorie) {
            if (k.getID() == id) {
                k.setNazwa(nazwa);
                break;
            }
        }
    }
    
    public Kategoria getKategoriaByID(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID() == id) {
                return k;
            }
        }

        return null;
    }
    
    public int getKategoriaRowByID(int id) {
        for (int i=0; i < listaKategorie.size(); i++) {
            if (listaKategorie.get(i).getID() == id) {
                return i;
            }
        }

        return -1;
    }

    public int getKategoria(int id) {
        return listaKategorie.get(id).getID();
    }

    public ArrayList<Kategoria> getKategorie() {
        return listaKategorie;
    }
}
