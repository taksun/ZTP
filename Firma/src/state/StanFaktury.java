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
public class StanFaktury extends Stan {
    @Override
    public void setPanel(JPanel p) {
        
        p.removeAll();
        
        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);
        
        //Add buttons to the experiment layout
        p.add(new JButton("Button 1"));
        p.add(new JButton("Button 2"));
        p.add(new JButton("Button 3"));
        p.add(new JButton("Long-Named Button 4"));
        p.add(new JButton("5"));
        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.validate();
        p.repaint();
    }
}
