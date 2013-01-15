/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author taksun
 */
public class OsobaPrywatna extends Klient {
    private String imie;
    private String nazwisko;
    
    public OsobaPrywatna(int id, String _imie, String _nazwisko, String _adres, String _kod, String _miejscowosc, String _telefon, String _nip) {
        super(id,_adres,_kod,_miejscowosc,_telefon,_nip);
        imie = _imie;
        nazwisko = _nazwisko;        
    }
    
    @Override
    public String getNazwa() {
        return "Os. pryw:" + nazwisko + " " + imie;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    
    @Override
    public String toString() {
        return this.getNazwa();
    }
}
