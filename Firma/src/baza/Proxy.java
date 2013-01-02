/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

/**
 *
 * @author taksun
 */
public class Proxy implements Interface {
    private MyDB baza;
    
    public Proxy() {
        baza = MyDB.getInstance();
    }
}
