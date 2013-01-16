/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author taksun
 */
public class Firma extends Klient {
    private String nazwa;
    private String regon;
    
    public Firma(int id, String _nazwa, String _adres, String _kod, String _miejscowosc, String _telefon, String _nip, String _regon) {
        super(id,_adres,_kod,_miejscowosc,_telefon,_nip);
        nazwa = _nazwa;
        regon = _regon;        
    }
    
    @Override
    public String getNazwa() {
        return nazwa;
    }
    
    public String getNazwa2() {
        return nazwa;
    }
    
    public void setNazwa(String n) {
        nazwa = n;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }
    
    @Override
    public String toString() {
        return this.getNazwa();
    }
}
