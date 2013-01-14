/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import baza.Firma;
import baza.Kategoria;
import baza.Klient;
import baza.MyDB;
import baza.OsobaPrywatna;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author taksun
 */
public class KlientOkno extends JDialog {

    MyDB baza;

    public KlientOkno() {

        baza = MyDB.getInstance();

        setTitle("Klienci");
        setModal(true);
        setSize(250, 300);

        final JPanel panel = new JPanel(new BorderLayout());

        final DefaultListModel model = new DefaultListModel();
        ArrayList<Klient> lista = baza.getKlienci();
        for (Klient k : lista) {
            model.addElement(k.getNazwa());
        }
        final JList klienci = new JList<>(model);

        panel.add(klienci, BorderLayout.CENTER);

        JPanel pRight = new JPanel();
        pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));

        JButton dodaj = new JButton("Dodaj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DodajKlientOkno okno = new DodajKlientOkno();
                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);

                if (okno.dodane) {
                    if (okno.firma.isSelected()) {
                        baza.addKlientFirma(okno.nazwa.getText(), okno.adres.getText(), okno.kod.getText(), okno.miejscowosc.getText(), okno.telefon.getText(), okno.nip.getText(), okno.regon.getText());
                        model.addElement("Firma: " + okno.nazwa.getText());
                    }

                    if (okno.ospryw.isSelected()) {
                        baza.addKlientOsobaPrywatna(okno.imie.getText(), okno.nazwisko.getText(), okno.adres.getText(), okno.kod.getText(), okno.miejscowosc.getText(), okno.telefon.getText(), okno.nip.getText());
                        model.addElement("Os pryw: " + okno.nazwisko.getText() + " " + okno.nazwisko.getText());
                    }
                }
            }
        });

        pRight.add(dodaj);

        JButton edytuj = new JButton("Edytuj");

        edytuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (klienci.getSelectedIndex() < 0) {
                    return;
                }

                Klient k = baza.getKlient(klienci.getSelectedIndex());

                DodajKlientOkno okno = new DodajKlientOkno();
                okno.setTitle("Edytuj klienta nr: " + Integer.toString(k.getID()));
                okno.dodaj.setText("Zapisz");

                okno.adres.setText(k.getAdres());
                okno.kod.setText(k.getKod());
                okno.miejscowosc.setText(k.getMiejscowosc());
                okno.nip.setText(k.getKod());
                okno.telefon.setText(k.getTelefon());


                if (k.getClass().getName().equals("baza.Firma")) {
                    okno.firma.setSelected(true);
                    okno.nazwa.setText(((Firma) k).getNazwa2());
                    okno.regon.setText(((Firma) k).getRegon());
                }

                if (k.getClass().getName().equals("baza.OsobaPrywatna")) {
                    okno.ospryw.setSelected(true);
                    okno.actionPerformed(null);
                    okno.imie.setText(((OsobaPrywatna) k).getImie());
                    okno.nazwisko.setText(((OsobaPrywatna) k).getNazwisko());
                }

                okno.firma.setEnabled(false);
                okno.ospryw.setEnabled(false);

                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);


                if (okno.dodane) {
                    k.setAdres(okno.adres.getText());
                    k.setKod(okno.kod.getText());
                    k.setMiejscowosc(okno.miejscowosc.getText());
                    k.setNip(okno.nip.getText());
                    k.setTelefon(okno.telefon.getText());

                    if (k.getClass().getName().equals("baza.Firma")) {
                        Firma f = (Firma)k;
                        f.setNazwa(okno.nazwa.getText());
                        f.setRegon(okno.regon.getText());
                    }

                    if (k.getClass().getName().equals("baza.OsobaPrywatna")) {
                        OsobaPrywatna op = (OsobaPrywatna)k;
                        op.setImie(okno.imie.getText());
                        op.setNazwisko(okno.nazwisko.getText());
                    }
                    
                    model.setElementAt(k.getNazwa(), klienci.getSelectedIndex());
                }
            }
        });

        pRight.add(edytuj);

        JButton usun = new JButton("Usuń");

        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (klienci.getSelectedIndex() < 0) {
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Czy na pewno usunąć klienta?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    baza.removeKlient(klienci.getSelectedIndex());
                    model.removeElementAt(klienci.getSelectedIndex());
                }

            }
        });

        pRight.add(usun);

        JButton anuluj = new JButton("Zamknij");

        anuluj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        pRight.add(anuluj);

        panel.add(pRight, BorderLayout.LINE_END);

        getContentPane().add(BorderLayout.CENTER, panel);
    }
}
