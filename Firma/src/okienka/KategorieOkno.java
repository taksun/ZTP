/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import baza.Kategoria;
import baza.MyDB;
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
public class KategorieOkno extends JDialog {

    MyDB baza;

    public KategorieOkno() {

        baza = MyDB.getInstance();

        setTitle("Kategorie");
        setModal(true);
        setSize(200, 250);

        JPanel panel = new JPanel(new BorderLayout());

        final DefaultListModel model = new DefaultListModel();
        ArrayList<Kategoria> lista = baza.getKategorie();
        for (Kategoria k : lista) {
            model.addElement(k.getNazwa());
        }
        final JList kategorie = new JList<>(model);

        panel.add(kategorie, BorderLayout.CENTER);

        JPanel pRight = new JPanel();
        pRight.setLayout(new BoxLayout(pRight, BoxLayout.Y_AXIS));

        JButton dodaj = new JButton("Dodaj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = (String) JOptionPane.showInputDialog(
                        null,
                        "Podaj nazwę kategorii",
                        "Dodanie kategorii",
                        JOptionPane.PLAIN_MESSAGE);
                if ((s != null) && (s.length() > 0)) {
                    baza.addKategoria(s);
                    model.addElement(s);
                }
            }
        });

        pRight.add(dodaj);

        JButton edytuj = new JButton("Edytuj");

        edytuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kategorie.getSelectedIndex() < 0) {
                    return;
                }
                String s = (String) JOptionPane.showInputDialog(
                        null,
                        "Zmień nazwę kategorii", baza.getKategoriaName(kategorie.getSelectedIndex()));
                if ((s != null) && (s.length() > 0)) {
                    int index = kategorie.getSelectedIndex();
                    baza.editKategoria(index, s);
                    model.setElementAt(s, index);
                }
            }
        });

        pRight.add(edytuj);

        JButton usun = new JButton("Usuń");

        usun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kategorie.getSelectedIndex() < 0) {
                    return;
                }

                if (baza.isKategoriaUsed(kategorie.getSelectedIndex())) {
                    JOptionPane.showMessageDialog(null, "Kategoria jest aktualnie przypisana do jakiegoś produktu. Nie można jej usunąć.", "Kategoria w użyciu", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        null,
                        "Czy na pewno usunąć kategorię?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    baza.removeKategoria(kategorie.getSelectedIndex());
                    model.removeElementAt(kategorie.getSelectedIndex());
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
