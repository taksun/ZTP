/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import baza.MyDB;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author taksun
 */
public class DodajZamowienieProduktOkno extends JDialog {

    public JTextField ilosc;
    public JComboBox produkt;
    JDialog okno;
    
    public Boolean dodane;
    
    public JButton dodaj;

    public DodajZamowienieProduktOkno() {

        okno = this;

        dodane = false;

        MyDB baza = MyDB.getInstance();

        setTitle("Dodaj produkt");
        setModal(true);
        setSize(300, 150);

        SpringLayout layout = new SpringLayout();
        
        JPanel panel = new JPanel(layout);

        JLabel l = new JLabel("Produkt", JLabel.TRAILING);
        produkt = new JComboBox<>(baza.getProduktyAL().toArray());
        l.setLabelFor(produkt);
        panel.add(l);
        panel.add(produkt);

        l = new JLabel("Ilość", JLabel.TRAILING);
        ilosc = new JTextField(10);
        ilosc.setText("1");
        l.setLabelFor(ilosc);
        panel.add(l);
        panel.add(ilosc);

        dodaj = new JButton("Dodaj");
        JButton anuluj = new JButton("Anuluj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (ilosc.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś ilości produktu", "Brak ilości", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int l = Integer.parseInt(ilosc.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(okno, "Podana ilość nie jest liczbą", "Błędna ilość", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                dodane = true;
                okno.setVisible(false);
            }
        });

        anuluj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                okno.setVisible(false);
            }
        });

        
        panel.add(anuluj);
        panel.add(dodaj);
        
        
        
        SpringUtilities.makeCompactGrid(panel,
                                3, 2, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad

        getContentPane().add(BorderLayout.CENTER, panel);
    }
}