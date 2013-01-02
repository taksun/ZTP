/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import javax.swing.JPanel;

/**
 *
 * @author taksun
 */
public class Chain {
    private Stan current;
    
    public Chain() {
       current = new StanFaktury();
    }
    public void set_state(Stan s) {
        current = s;
    }
    
    public void setPanel(JPanel p) {
        current.setPanel(p);
    }
}
