/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author taksun
 */
public class Faktura implements Serializable {
    private int fakID;
    private Date data;
    private int zamID;
    private boolean oplacona;
    
    public Faktura(int fID, int zID) {
        fakID = fID;
        data = new Date();
        zamID = zID;
        oplacona = false;
    }
    
    public Object[] toTable() {
        String c = "Nie";
        if (oplacona) {
            c = "Tak";
        }
        Object[] tab = {fakID, data, zamID, c};
        return tab;
    }

    public boolean isOplacona() {
        return oplacona;
    }
    
    public void changeOplacone() {
        oplacona = !oplacona;
    }

    public int getID() {
        return fakID;
    }

    public int getZamID() {
        return zamID;
    }
    
    public String getDate() {
        return data.toString();
    }
}
