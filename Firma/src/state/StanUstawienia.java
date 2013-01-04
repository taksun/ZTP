/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import firma.Ustawienia;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author taksun
 */
public class StanUstawienia extends Stan {
    Ustawienia ust = Ustawienia.getInstance();
    
    JTextField kurs;
    
    @Override
    public void setPanel(JPanel p) {
        
        p.removeAll();
        
        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);
        
        p.add(new JLabel("Aktualy kurs €"));
        
        kurs = new JTextField(3);
        kurs.setText(Float.toString(ust.getKurs()));
        p.add(kurs);
        
        JButton btnEdit = new JButton("Zmień");

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                try {
                    String text = kurs.getText();
                    text = text.replace(',', '.');
                    kurs.setText(text);
                    Float.parseFloat(kurs.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(null, "Podany kurs nie jest liczbą", "Błędny kurs", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                ust.setKurs(Float.parseFloat(kurs.getText()));
                JOptionPane.showMessageDialog(null, "Kurs został zmieniony", "Kurs zmieniony", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        p.add(btnEdit);
        
        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.validate();
        p.repaint();
    }
}
