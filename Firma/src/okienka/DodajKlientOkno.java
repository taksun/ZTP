/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author taksun
 */
public class DodajKlientOkno extends JDialog implements ActionListener {

    public JTextField imie;
    public JTextField nazwisko;
    public JTextField nazwa;
    public JTextField adres;
    public JTextField kod;
    public JTextField miejscowosc;
    public JTextField telefon;
    public JTextField nip;
    public JTextField regon;
    
    JDialog okno;
    JPanel panel;
    
    public Boolean dodane;
    public JButton dodaj;
    JButton anuluj;
    public JRadioButton firma;
    public JRadioButton ospryw;
    JLabel limie;
    JLabel lnazwisko;
    JLabel lnazwa;
    JLabel ladres;
    JLabel lkod;
    JLabel lmsc;
    JLabel ltel;
    JLabel lnip;
    JLabel lregon;

    public DodajKlientOkno() {

        okno = this;

        dodane = false;

        setTitle("Dodaj klienta");
        setModal(true);
        setSize(250, 340);

        SpringLayout layout = new SpringLayout();

        panel = new JPanel(layout);

        firma = new JRadioButton("Firma");
        firma.setSelected(true);

        firma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();

                panel.add(firma);
                panel.add(ospryw);
                panel.add(lnazwa);
                panel.add(nazwa);
                panel.add(ladres);
                panel.add(adres);
                panel.add(lkod);
                panel.add(kod);
                panel.add(lmsc);
                panel.add(miejscowosc);
                panel.add(ltel);
                panel.add(telefon);
                panel.add(lnip);
                panel.add(nip);
                panel.add(lregon);
                panel.add(regon);
                panel.add(anuluj);
                panel.add(dodaj);

                SpringUtilities.makeCompactGrid(panel,
                        9, 2, //rows, cols
                        6, 6, //initX, initY
                        6, 6);       //xPad, yPad

                panel.validate();
                panel.repaint();
            }
        });

        panel.add(firma);

        ospryw = new JRadioButton("Osoba Prywatna");

        ospryw.addActionListener(this);

        panel.add(ospryw);

        ButtonGroup group = new ButtonGroup();
        group.add(firma);
        group.add(ospryw);

        limie = new JLabel("Imię", JLabel.TRAILING);
        imie = new JTextField(10);
        limie.setLabelFor(imie);

        lnazwisko = new JLabel("Nazwisko", JLabel.TRAILING);
        nazwisko = new JTextField(10);
        lnazwisko.setLabelFor(nazwisko);

        lnazwa = new JLabel("Nazwa firmy", JLabel.TRAILING);
        nazwa = new JTextField(10);
        lnazwa.setLabelFor(nazwa);
        panel.add(lnazwa);
        panel.add(nazwa);

        ladres = new JLabel("Adres", JLabel.TRAILING);
        adres = new JTextField(10);
        ladres.setLabelFor(adres);
        panel.add(ladres);
        panel.add(adres);

        lkod = new JLabel("Kod pocztowy", JLabel.TRAILING);
        kod = new JTextField(10);
        lkod.setLabelFor(kod);
        panel.add(lkod);
        panel.add(kod);

        lmsc = new JLabel("Miejscowosc", JLabel.TRAILING);
        miejscowosc = new JTextField(10);
        lmsc.setLabelFor(miejscowosc);
        panel.add(lmsc);
        panel.add(miejscowosc);

        ltel = new JLabel("Telefon", JLabel.TRAILING);
        telefon = new JTextField(10);
        ltel.setLabelFor(telefon);
        panel.add(ltel);
        panel.add(telefon);

        lnip = new JLabel("NIP", JLabel.TRAILING);
        nip = new JTextField(10);
        lnip.setLabelFor(nip);
        panel.add(lnip);
        panel.add(nip);

        lregon = new JLabel("REGON", JLabel.TRAILING);
        regon = new JTextField(10);
        lregon.setLabelFor(regon);
        panel.add(lregon);
        panel.add(regon);

        dodaj = new JButton("Dodaj");
        anuluj = new JButton("Anuluj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (firma.isSelected()) {
                    if (nazwa.getText().equals("")) {
                        JOptionPane.showMessageDialog(okno, "Nie podałeś nazwy firmy", "Brak nazwy", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (ospryw.isSelected()) {
                    if (imie.getText().equals("")) {
                        JOptionPane.showMessageDialog(okno, "Nie podałeś imienia", "Brak imienie", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (nazwisko.getText().equals("")) {
                        JOptionPane.showMessageDialog(okno, "Nie podałeś nazwiska", "Brak nazwiska", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (adres.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś adresu", "Brak adresu", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (kod.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś kodu pocztowego", "Brak kodu pocztowego", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (miejscowosc.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś miejscowosci", "Brak miejscowosci", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (nip.getText().equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś NIP", "Brak NIP", JOptionPane.WARNING_MESSAGE);
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
                9, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        getContentPane().add(BorderLayout.CENTER, panel);
    }

    public void actionPerformed(ActionEvent e) {
        panel.removeAll();

        panel.add(firma);
        panel.add(ospryw);
        panel.add(limie);
        panel.add(imie);
        panel.add(lnazwisko);
        panel.add(nazwisko);
        panel.add(ladres);
        panel.add(adres);
        panel.add(lkod);
        panel.add(kod);
        panel.add(lmsc);
        panel.add(miejscowosc);
        panel.add(ltel);
        panel.add(telefon);
        panel.add(lnip);
        panel.add(nip);
        panel.add(anuluj);
        panel.add(dodaj);

        SpringUtilities.makeCompactGrid(panel,
                9, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad

        panel.validate();
        panel.repaint();
    }
}
