/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author Karolina
 */
public class Kategoria {
    
    private int kategoriaID;
    private String nazwa;
    
    public Kategoria(int kID,String n) {
        kategoriaID = kID;
        nazwa = n;
    }
    
    public int getID() {
        return kategoriaID;
    }
    
    public void setNazwa(String n) {
        nazwa = n;
    }
    
    public String getNazwa() {
        return nazwa;
    }
}
