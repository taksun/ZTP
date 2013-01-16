/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import baza.MyDB;
import baza.Zamowienie;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author taksun
 */
public class DodajFaktureOkno extends JDialog {
    
    public ArrayList<Zamowienie> alist;
    
    public JComboBox zamowienia;
    JDialog okno;
    public Boolean dodane;
    public Boolean pusto;
    public JButton dodaj;

    public DodajFaktureOkno() {

        okno = this;

        dodane = false;
        pusto = false;

        setTitle("Dodaj fakture");
        setModal(true);
        setSize(300, 120);


        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel kpanel = new JPanel();
        JLabel l = new JLabel("Zamowienia", JLabel.TRAILING);
        
        MyDB baza = MyDB.getInstance();
        
        alist = baza.getZamowieniaBezFaktur();
        
        if (alist.isEmpty()) {
            pusto = true;
            return;
        }
        
        zamowienia = new JComboBox<>(alist.toArray());
        l.setLabelFor(zamowienia);
        kpanel.add(l);
        kpanel.add(zamowienia);

        panel.add(kpanel);

        JPanel pBot = new JPanel();

        dodaj = new JButton("Dodaj Zamówienie");
        JButton anuluj = new JButton("Anuluj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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


        pBot.add(anuluj);
        pBot.add(dodaj);

        panel.add(pBot);


        getContentPane().add(BorderLayout.CENTER, panel);
    }
}
