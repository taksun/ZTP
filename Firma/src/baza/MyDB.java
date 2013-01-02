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
    
    private ArrayList<Produkt> listaProdukty = new ArrayList<>();
    private ArrayList<Kategoria> listaKategorie = new ArrayList<>();
    
    protected MyDB() {
        
    }
    
    public static MyDB getInstance() {
        if (singleton == null) {
            singleton = new MyDB();
        }
        return singleton;
    }
    
    public void addProdukt(Produkt p) {
        listaProdukty.add(p);
    }
    
    public void removeProdukt(int id) {
        for (Produkt p : listaProdukty) {
            if (p.getID()==id) {
                listaProdukty.remove(p);
                break;
            }
        }
    }
    
    public void editProdukt(int id, String nazwa) {
        for (Produkt p : listaProdukty) {
            if (p.getID()==id) {
                p.setNazwa(nazwa);
                break;
            }
        }
    }
    
    public Produkt getProdukt(int id) {
        for (Produkt p : listaProdukty) {
            if (p.getID()==id) {
                return p;
            }
        }
        
        return null;
    }
    
    public ArrayList<Produkt> getProdukty() {
        return listaProdukty;
    }
    
    public void addKategoria(String nazwa) {
        int kategoriaID = 0; // jakos auto increment
        listaKategorie.add(new Kategoria(kategoriaID,nazwa));
    }
    
    public void removeKategoria(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID()==id) {
                listaKategorie.remove(k);

                break;
            }
        }
    }
    
    public void editKategoria(int id, String nazwa) {
        for (Kategoria k : listaKategorie) {
            if (k.getID()==id) {
                k.setNazwa(nazwa);
                break;
            }
        }
    }
    
    public Kategoria getKategoria(int id) {
        for (Kategoria k : listaKategorie) {
            if (k.getID()==id) {
                return k;
            }
        }
        
        return null;
    }
    
    public ArrayList<Kategoria> getKategorie() {
        return listaKategorie;
    }
}
