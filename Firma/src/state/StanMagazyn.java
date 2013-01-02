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
public class StanMagazyn extends Stan {
    @Override
    public void setPanel(JPanel p) {
        
        p.removeAll();
        
        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.TRAILING);
        
        //Add buttons to the experiment layout
        p.add(new JButton("M"));
        p.add(new JButton("A"));
        p.add(new JButton("G"));
        p.add(new JButton("A"));
        p.add(new JButton("Z"));
        p.add(new JButton("Y"));
        p.add(new JButton("N"));
        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.validate();
        p.repaint();
    }
}
