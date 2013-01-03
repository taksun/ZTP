/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author taksun
 */
public class Klient {
    private int klientID;
    private String adres;
    private String kod;
    private String miejscowosc;
    private String telefon;
    private String nip;
    
    public Klient(int id, String _adres, String _kod, String _miejscowosc, String _telefon, String _nip) {
        klientID = id;
        adres = _adres;
        kod = _kod;
        miejscowosc = _miejscowosc;
        telefon = _telefon;
        nip = _nip;
    }
    
    public int getID() {
        return klientID;
    }
}
