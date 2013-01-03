/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author taksun
 */
public class StanUstawienia extends Stan {
    @Override
    public void setPanel(JPanel p) {
        
        p.removeAll();
        
        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);
        
        //Add buttons to the experiment layout
        p.add(new JButton("U"));
        p.add(new JButton("S"));
        p.add(new JButton("T"));
        p.add(new JButton("A"));
        p.add(new JButton("W"));
        p.add(new JButton("I"));
        p.add(new JButton("E"));
        p.add(new JButton("N"));
        p.add(new JButton("I"));
        p.add(new JButton("A"));
        
        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.validate();
        p.repaint();
    }
}
