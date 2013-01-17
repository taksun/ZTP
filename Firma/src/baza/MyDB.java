/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    private int nextFakturaID = 1;
    private ArrayList<Produkt> listaProdukty = new ArrayList<>();
    private ArrayList<Kategoria> listaKategorie = new ArrayList<>();
    private ArrayList<Klient> listaKlienci = new ArrayList<>();
    private ArrayList<Zamowienie> listaZamowienia = new ArrayList<>();
    private ArrayList<Faktura> listaFaktury = new ArrayList<>();

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
    
    public Klient getKlientByID(int id) {
        for (Klient k : listaKlienci) {
            if (k.getID() == id) {
                return k;
            }
        }

        return null;
    }

    @Override
    public int getKlientID(int nr) {
        return listaKlienci.get(nr).getID();
    }
    
    @Override
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

    @Override
    public void addZamowienie(int klientID, ArrayList<Produkt> produkty) {
        Zamowienie z = new Zamowienie(nextZamowienieID, "Nowe", produkty, klientID);
        nextZamowienieID++;
        listaZamowienia.add(z);
    }

    @Override
    public Zamowienie getZamowienie(int id) {
        return listaZamowienia.get(id);
    }
    
    public Zamowienie getZamowienieByID(int id) {
        for (Zamowienie z : listaZamowienia) {
            if (z.getID() == id) {
                return z;
            }
        }

        return null;
    }
    
    public int getZamowienieID(int id) {
        return listaZamowienia.get(id).getID();
    }

    @Override
    public Object[][] getZamowienia() {
        Object[][] data = new Object[listaZamowienia.size()][6];

        for (int i = 0; i < listaZamowienia.size(); i++) {
            data[i] = listaZamowienia.get(i).toTable();
        }

        return data;
    }
    
    public ArrayList<Zamowienie> getZamowieniaBezFaktur() {
        ArrayList<Zamowienie> al = new ArrayList<>();
        
        for (Zamowienie z: listaZamowienia) {
            if (z.getFakID()==0) {
                al.add(z);
            }
        }
        
        return al;
    }

    @Override
    public void removeZamowienie(int id) {
        listaZamowienia.remove(id);
    }

    @Override
    public Object[] getLastZamowienie() {
        return listaZamowienia.get(listaZamowienia.size() - 1).toTable();
    }
    
    public void addFaktura(int id) {
        Faktura f = new Faktura(nextFakturaID, id);
        nextFakturaID++;
        listaFaktury.add(f);
    }
    
    public Faktura getFaktura(int id) {
        return listaFaktury.get(id);
    }
    
    public Object[][] getFaktury() {
        Object[][] data = new Object[listaFaktury.size()][6];

        for (int i = 0; i < listaFaktury.size(); i++) {
            data[i] = listaFaktury.get(i).toTable();
        }

        return data;
    }
    
    public Object[] getLastFaktura() {
        return listaFaktury.get(listaFaktury.size() - 1).toTable();
    }
    
    public void save() throws FileNotFoundException, IOException {
        File f = new File("./baza/produkty.db");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaProdukty);
        }
        
        f = new File("./baza/kategorie.db");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaKategorie);
        }
        
        f = new File("./baza/klienci.db");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaKlienci);
        }
        
        f = new File("./baza/zamowienia.db");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaZamowienia);
        }
        
        f = new File("./baza/faktury.db");
        try (FileOutputStream fos = new FileOutputStream(f)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaFaktury);
        }
    }
    
    public void load() throws FileNotFoundException, IOException, ClassNotFoundException {
        File f = new File("./baza/produkty.db");
        try (FileInputStream fis = new FileInputStream(f)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaProdukty = (ArrayList<Produkt>)ois.readObject();
        }
        
        f = new File("./baza/kategorie.db");
        try (FileInputStream fis = new FileInputStream(f)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaKategorie = (ArrayList<Kategoria>)ois.readObject();
        }
        
        f = new File("./baza/klienci.db");
        try (FileInputStream fis = new FileInputStream(f)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaKlienci = (ArrayList<Klient>)ois.readObject();
        }
        
        f = new File("./baza/zamowienia.db");
        try (FileInputStream fis = new FileInputStream(f)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaZamowienia = (ArrayList<Zamowienie>)ois.readObject();
        }
        
        f = new File("./baza/faktury.db");
        try (FileInputStream fis = new FileInputStream(f)) {
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaFaktury = (ArrayList<Faktura>)ois.readObject();
        }
    }
}
