/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import firma.Ustawienia;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author taksun
 */
public class StanUstawienia extends Stan {
    Ustawienia ust = Ustawienia.getInstance();
    
    @Override
    public void setPanel(JPanel p) {
        
        p.removeAll();
        
        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);
        
        p.add(new Label("Kurs euro: " + Float.toString(ust.getKurs())));
        
        JButton btnEdit = new JButton("Edytuj");

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                
                ust.setKurs(5.9F);
            }
        });

        p.add(btnEdit);
        
        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.validate();
        p.repaint();
    }
}
