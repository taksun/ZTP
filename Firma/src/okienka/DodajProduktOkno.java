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

    public DodajProduktOkno() {

        okno = this;

        dodane = false;

        MyDB baza = MyDB.getInstance();

        setTitle("Dodaj produkt");
        setModal(true);
        setSize(200, 250);

        JPanel panel = new JPanel();

        nazwa = new JTextField(10);
        panel.add(new JLabel("Nazwa"));
        panel.add(nazwa);

        kategoria = new JComboBox<>(baza.getKategorie().toArray());
        panel.add(new JLabel("Kategoria"));
        panel.add(kategoria);

        ilosc = new JTextField(10);
        panel.add(new JLabel("Ilość"));
        panel.add(ilosc);

        cena = new JTextField(10);
        panel.add(new JLabel("Cena"));
        panel.add(cena);

        String[] vatT = {"23", "7", "5", "3"};

        vat = new JComboBox<>(vatT);
        panel.add(new JLabel("VAT"));
        panel.add(vat);

        JButton dodaj = new JButton("Dodaj");
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

        panel.add(dodaj);
        panel.add(anuluj);

        getContentPane().add(BorderLayout.CENTER, panel);
    }
}
