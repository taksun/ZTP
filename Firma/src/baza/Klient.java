/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.Serializable;

/**
 *
 * @author taksun
 */
public class Klient implements Serializable {
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
    
    public String getNazwa() {
        return "";
    }
    
    public String getDane() {
        return adres + "\n" + kod + " " + miejscowosc + "\n" + "NIP: " + nip;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }
    
}
