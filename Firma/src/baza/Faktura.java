/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import java.util.Date;

/**
 *
 * @author taksun
 */
public class Faktura {
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
        Object[] tab = {fakID, data, zamID, oplacona};
        return tab;
    }
    
    public void changeOplacone() {
        oplacona = !oplacona;
    }
}
