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
public class DodajProduktOkno extends JDialog {

    public JTextField nazwa;
    public JTextField ilosc;
    public JTextField cena;
    public JComboBox kategoria;
    public JComboBox vat;
    JDialog okno;
    public Boolean dodane;
    public JButton dodaj;

    public DodajProduktOkno() {

        okno = this;

        dodane = false;

        MyDB baza = MyDB.getInstance();

        setTitle("Dodaj produkt");
        setModal(true);
        setSize(200, 250);

        SpringLayout layout = new SpringLayout();
        
        JPanel panel = new JPanel(layout);

        JLabel l = new JLabel("Nazwa", JLabel.TRAILING);
        nazwa = new JTextField(10);
        l.setLabelFor(nazwa);
        panel.add(l);
        panel.add(nazwa);

        l = new JLabel("Kategoria", JLabel.TRAILING);
        kategoria = new JComboBox<>(baza.getKategorie().toArray());
        l.setLabelFor(kategoria);
        panel.add(l);
        panel.add(kategoria);

        l = new JLabel("Ilość", JLabel.TRAILING);
        ilosc = new JTextField(10);
        l.setLabelFor(ilosc);
        panel.add(l);
        panel.add(ilosc);

        l = new JLabel("Cena", JLabel.TRAILING);
        cena = new JTextField(10);
        l.setLabelFor(cena);
        panel.add(l);
        panel.add(cena);

        String[] vatT = {"23", "7", "5", "3"};

        l = new JLabel("VAT", JLabel.TRAILING);
        vat = new JComboBox<>(vatT);
        l.setLabelFor(vat);
        panel.add(l);
        panel.add(vat);

        dodaj = new JButton("Dodaj");
        JButton anuluj = new JButton("Anuluj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (nazwa.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś nazwy produktu", "Brak nazwy", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (ilosc.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś ilości produktu", "Brak ilości", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (cena.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś ceny produktu", "Brak Ceny", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int l = Integer.parseInt(ilosc.getText());
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(okno, "Podana ilość nie jest liczbą", "Błędna ilość", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    String text = cena.getText();
                    text = text.replace(',', '.');
                    cena.setText(text);
                    Float c = Float.parseFloat(cena.getText());
                    c = c * 100;
                    c = (float)Math.round(c);
                    c = c / 100;
                    cena.setText(Float.toString(c));
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(okno, "Podana cena nie jest liczbą", "Błędna cena", JOptionPane.WARNING_MESSAGE);
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
                                6, 2, //rows, cols
                                6, 6,        //initX, initY
                                6, 6);       //xPad, yPad

        getContentPane().add(BorderLayout.CENTER, panel);
    }
}
