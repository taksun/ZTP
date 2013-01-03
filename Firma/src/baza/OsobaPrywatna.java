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
}
