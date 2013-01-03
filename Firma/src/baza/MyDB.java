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
    private ArrayList<Produkt> listaProdukty = new ArrayList<>();
    private ArrayList<Kategoria> listaKategorie = new ArrayList<>();
    private ArrayList<Klient> listaKlienci = new ArrayList<>();

    protected MyDB() {
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
        for (Produkt p : listaProdukty) {
            if (p.getID() == id) {
                listaProdukty.remove(p);
                break;
            }
        }
    }

    public void editProdukt(int id, String nazwa) {
        for (Produkt p : listaProdukty) {
            if (p.getID() == id) {
                p.setNazwa(nazwa);
                break;
            }
        }
    }

    public Produkt getProdukt(int id) {
        for (Produkt p : listaProdukty) {
            if (p.getID() == id) {
                return p;
            }
        }

        return null;
    }

    public ArrayList<Produkt> getProdukty() {
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

    public Kategoria getKategoria(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID() == id) {
                return k;
            }
        }

        return null;
    }

    public ArrayList<Kategoria> getKategorie() {
        return listaKategorie;
    }

    public void addKlient(String imie, String nazwisko, String adres, String kod, String miejscowosc, String telefon, String nip, boolean firma) {
        // dla firmy podajemy true oraz w imie podajemy nazwe a w nazwisko regon
        
        int klientID = nextKlientID;
        nextKlientID++;
        
        if (firma) {
            listaKlienci.add(new Firma(klientID, imie, nazwisko, adres, kod, miejscowosc, telefon, nip));
        } else {
            listaKlienci.add(new OsobaPrywatna(klientID, imie, nazwisko, adres, kod, miejscowosc, telefon, nip));
        }
    }

    public void removeKlient(int id) {
        for (Klient k : listaKlienci) {
            if (k.getID() == id) {
                listaKlienci.remove(k);

                break;
            }
        }
    }

    public Klient getKlient(int id) {
        for (Klient k : listaKlienci) {
            if (k.getID() == id) {
                return k;
            }
        }

        return null;
    }

    public ArrayList<Klient> getKlienci() {
        return listaKlienci;
    }
}
