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
        listaProdukty.remove(id);
        /*for (Produkt p : listaProdukty) {
            if (p.getID() == id) {
                listaProdukty.remove(p);
                break;
            }
        }*/
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

    public Object[][] getProdukty() {
        Object [][] data = new Object[listaProdukty.size()][6];
        
        for (int i=0; i<listaProdukty.size(); i++) {
            data[i] = listaProdukty.get(i).toTable();
        } 
        
        return data;
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

    public String getKategoria(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID() == id) {
                return k.getNazwa();
            }
        }

        return "";
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
    
    public void addZamowienie(ArrayList<Produkt> produkty, int klientID) {
        Zamowienie z = new Zamowienie(nextZamowienieID, "Nowe", produkty, klientID);
        nextZamowienieID++;
        listaZamowienia.add(z);
    }
    
    public void editZamowienieStatus(int id, String status) {
        for (Zamowienie z : listaZamowienia) {
            if (z.getID() == id) {
                z.setStatus(status);

                break;
            }
        }
    }
    
    public void removeZamowienie(int id) {
        for (Zamowienie z : listaZamowienia) {
            if (z.getID() == id) {
                listaZamowienia.remove(z);

                break;
            }
        }
    }
      
    public Zamowienie getZamowienie(int id) {
        for (Zamowienie z : listaZamowienia) {
            if (z.getID() == id) {
                return z;
            }
        }

        return null;
    }
    
    public ArrayList<Zamowienie> getZamowienia() {
        return listaZamowienia;
    }
}
