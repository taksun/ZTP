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
        return listaProdukty.get(listaProdukty.size() - 1).toTable();
    }

    public Produkt getProdukt(int id) {
        return listaProdukty.get(id);
    }
    
    public ArrayList<Produkt> getProduktyAL() {
        return listaProdukty;
    }

    public Object[][] getProdukty() {
        Object[][] data = new Object[listaProdukty.size()][];

        for (int i = 0; i < listaProdukty.size(); i++) {
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
        listaKategorie.remove(id);
    }

    public void editKategoria(int id, String nazwa) {
        listaKategorie.get(id).setNazwa(nazwa);
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
        for (int i = 0; i < listaKategorie.size(); i++) {
            if (listaKategorie.get(i).getID() == id) {
                return i;
            }
        }

        return -1;
    }

    public int getKategoria(int id) {
        return listaKategorie.get(id).getID();
    }

    public String getKategoriaName(int id) {
        return listaKategorie.get(id).getNazwa();
    }

    public ArrayList<Kategoria> getKategorie() {
        return listaKategorie;
    }

    public boolean isKategoriaUsed(int nr) {
        int id = listaKategorie.get(nr).getID();

        for (Produkt p : listaProdukty) {
            if (p.getKategoria() == id) {
                return true;
            }
        }

        return false;
    }

    public void addKlientFirma(String nazwa, String adres, String kod, String miejscowosc, String telefon, String nip, String regon) {
        int klientID = nextKlientID;
        nextKlientID++;
        listaKlienci.add(new Firma(klientID, nazwa, adres, kod, miejscowosc, telefon, nip, regon));
    }

    public void addKlientOsobaPrywatna(String imie, String nazwisko, String adres, String kod, String miejscowosc, String telefon, String nip) {
        int klientID = nextKlientID;
        nextKlientID++;
        listaKlienci.add(new OsobaPrywatna(klientID, imie, nazwisko, adres, kod, miejscowosc, telefon, nip));
    }

    public Klient getKlient(int nr) {
        return listaKlienci.get(nr);
    }

    public int getKlientID(int nr) {
        return listaKlienci.get(nr).getID();
    }
    
    public int getKlientRowByID(int id) {
        for (int i = 0; i < listaKlienci.size(); i++) {
            if (listaKlienci.get(i).getID() == id) {
                return i;
            }
        }

        return -1;
    }

    public ArrayList<Klient> getKlienci() {
        return listaKlienci;
    }

    public void removeKlient(int id) {
        listaKlienci.remove(id);
    }

    public void addZamowienie(int klientID, ArrayList<Produkt> produkty) {
        Zamowienie z = new Zamowienie(nextZamowienieID, "Nowe", produkty, klientID);
        nextZamowienieID++;
        listaZamowienia.add(z);
    }

    public Zamowienie getZamowienie(int id) {
        return listaZamowienia.get(id);
    }

    public Object[][] getZamowienia() {
        Object[][] data = new Object[listaZamowienia.size()][6];

        for (int i = 0; i < listaZamowienia.size(); i++) {
            data[i] = listaZamowienia.get(i).toTable();
        }

        return data;
    }

    public void removeZamowienie(int id) {
        listaZamowienia.remove(id);
    }

    public Object[] getLastZamowienie() {
        return listaZamowienia.get(listaZamowienia.size() - 1).toTable();
    }
}
