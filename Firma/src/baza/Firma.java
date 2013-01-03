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
    
    public Firma(int id, String _nazwa, String _regon, String _adres, String _kod, String _miejscowosc, String _telefon, String _nip) {
        super(id,_adres,_kod,_miejscowosc,_telefon,_nip);
        nazwa = _nazwa;
        regon = _regon;        
    }
}
