/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.Serializable;

/**
 *
 * @author Karolina
 */
public class Kategoria implements Serializable {
    
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
    
    @Override
    public String toString() {
        return nazwa;
    }
}
