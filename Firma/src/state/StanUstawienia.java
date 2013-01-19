/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import firma.Ustawienia;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import okienka.KategorieOkno;
import okienka.KlientOkno;
import okienka.SpringUtilities;

/**
 *
 * @author taksun
 */
public class StanUstawienia extends Stan {
    Ustawienia ust = Ustawienia.getInstance();
    
    JTextField kurs;
    JTextField nazwa;
    JTextField adres;
    JTextField miejscowosc;
    JTextField kod;
    JTextField nip;
    JTextField regon;
    
    JPanel panel;
    
    @Override
    public void setPanel(JPanel p) {
        
        panel = p;
        
        p.removeAll();
        
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        
        FlowLayout experimentLayout = new FlowLayout();
        JPanel pKurs = new JPanel(experimentLayout);
        //p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);

        pKurs.add(new JLabel("Aktualy kurs €"));
        pKurs.setMaximumSize(new Dimension(1000,40));
        
        kurs = new JTextField(3);
        kurs.setText(Float.toString(ust.getKurs()));
        pKurs.add(kurs);
        
        
        
        JButton btnEdit = new JButton("Zmień Kurs");

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

        pKurs.add(btnEdit);
        
        //Left to right component orientation is selected by default
        pKurs.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        
        p.add(pKurs);
        
        SpringLayout layout = new SpringLayout();
        
        JPanel pFirma2 = new JPanel(experimentLayout);
        JPanel pFirma = new JPanel(layout);
        
        //pFirma.setMaximumSize(new Dimension(1500,100));
        //pFirma.setBackground(Color.red);
        
        pFirma.add(new JLabel("Nazwa firmy"));
        nazwa = new JTextField(10);
        nazwa.setText(ust.getNazwa());
        pFirma.add(nazwa);
        
        pFirma.add(new JLabel("Adres"));
        adres = new JTextField(10);
        adres.setText(ust.getAdres());
        pFirma.add(adres);
        
        pFirma.add(new JLabel("Miejscowosc"));
        miejscowosc = new JTextField(10);
        miejscowosc.setText(ust.getMiejscowosc());
        pFirma.add(miejscowosc);
        
        pFirma.add(new JLabel("Kod pocztowy"));
        kod = new JTextField(10);
        kod.setText(ust.getKod());
        pFirma.add(kod);
        
        pFirma.add(new JLabel("NIP"));
        nip = new JTextField(10);
        nip.setText(ust.getNip());
        pFirma.add(nip);
        
        pFirma.add(new JLabel("Regon"));
        regon = new JTextField(10);
        regon.setText(ust.getRegon());
        pFirma.add(regon);
        
        JButton btnEditFirma = new JButton("Zmień dane");

        btnEditFirma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                if (nazwa.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Musisz podać nazwę firmy", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (adres.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Musisz podać adres firmy", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (miejscowosc.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Musisz podać miejscowość firmy", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (kod.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Musisz podać kod pocztowy firmy", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                if (nip.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Musisz podać NIP firmy", "Błędne dane", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                
                ust.setAdres(adres.getText());
                ust.setKod(kod.getText());
                ust.setMiejscowosc(miejscowosc.getText());
                ust.setNip(nip.getText());
                ust.setRegon(regon.getText());

                JOptionPane.showMessageDialog(null, "Dane firmy zostały zmienione", "Dane zmienione", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pFirma.add(new JLabel());
        pFirma.add(btnEditFirma);
        
        SpringUtilities.makeCompactGrid(pFirma,
                7, 2, //rows, cols
                6, 6, //initX, initY
                6, 6);       //xPad, yPad
        
        pFirma2.add(pFirma);
        p.add(pFirma2);
        
        JPanel pPrzyciski = new JPanel(experimentLayout);
        
        JButton btnCat = new JButton("Kategorie");

        btnCat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                KategorieOkno okno = new KategorieOkno();
                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);
            }
        });

        pPrzyciski.add(btnCat);
        
        JButton btnClient = new JButton("Klienci");

        btnClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {             
                
                KlientOkno okno = new KlientOkno();
                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);
            }
        });

        pPrzyciski.add(btnClient);
        
        p.add(pPrzyciski);
        
        p.validate();
        p.repaint();
    }
}
