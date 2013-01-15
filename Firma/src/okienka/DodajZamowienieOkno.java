/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package okienka;

import baza.MyDB;
import baza.Produkt;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author taksun
 */
public class DodajZamowienieOkno extends JDialog {

    int selectedRow = -1;
    
    MyDB baza = MyDB.getInstance();
    
    public ArrayList<Produkt> produkty;
    public JComboBox klient;
    JDialog okno;
    public Boolean dodane;
    public JButton dodaj;

    public DodajZamowienieOkno(ArrayList<Produkt> p) {
        
        produkty = p;

        okno = this;

        dodane = false;

        setTitle("Dodaj zamowienie");
        setModal(true);
        setSize(700, 300);


        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel kpanel = new JPanel();
        JLabel l = new JLabel("Klient", JLabel.TRAILING);
        klient = new JComboBox<>(baza.getKlienci().toArray());
        l.setLabelFor(klient);
        kpanel.add(l);
        kpanel.add(klient);

        panel.add(kpanel);

        String[] col = {"ID",
            "Nazwa",
            "Kategoria",
            "Ilość",
            "Cena",
            "Cena €",
            "VAT",
            "Cena z VAT",
            "Cena € z VAT"};

        Object[][] data = new Object[produkty.size()][];

        for (int i = 0; i < produkty.size(); i++) {
            data[i] = produkty.get(i).toTable();
        }

        final DefaultTableModel model = new DefaultTableModel(data, col);


        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };

        //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSM = table.getSelectionModel();
        rowSM.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                //Ignore extra messages.
                if (e.getValueIsAdjusting()) {
                    return;
                }

                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()) {
                    selectedRow = -1;
                } else {
                    selectedRow = lsm.getMinSelectionIndex();
                }
            }
        });

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        panel.add(scrollPane);

        JPanel pmenu = new JPanel();

        JButton pdodaj = new JButton("Dodaj produkt");

        pdodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodajZamowienieProduktOkno pokno = new DodajZamowienieProduktOkno();
                pokno.setLocationRelativeTo(okno);
                pokno.setVisible(true);

                if (pokno.dodane) {
                    Produkt p = new Produkt(baza.getProdukt(pokno.produkt.getSelectedIndex()));
                    p.setIlosc(Integer.parseInt(pokno.ilosc.getText()));
                    produkty.add(p);
                    model.addRow(p.toTable());
                }
            }
        });

        pmenu.add(pdodaj);

        JButton pzmien = new JButton("Zmień ilość produktu");

        pzmien.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(okno, "Nie wybraleś produktu!", "Brak wybranego produktu", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String s = JOptionPane.showInputDialog(null, "Podaj nową ilość", "Ustawienie ilości", JOptionPane.PLAIN_MESSAGE);
                
                if (s.equals("")) {
                    JOptionPane.showMessageDialog(okno, "Nie podałeś ilości produktu", "Brak ilości", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int l = 0;
                try {
                    l = Integer.parseInt(s);
                } catch (Exception exc) {
                    JOptionPane.showMessageDialog(okno, "Podana ilość nie jest liczbą", "Błędna ilość", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                if (l<1) {
                    JOptionPane.showMessageDialog(okno, "Podana ilość musi być większa od 0", "Błędna ilość", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                produkty.get(selectedRow).setIlosc(l);
                model.setValueAt(l, selectedRow, 3);
            }
        });

        pmenu.add(pzmien);

        JButton pusun = new JButton("Usuń produkt");

        pusun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(okno, "Nie wybraleś produktu!", "Brak wybranego produktu", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        okno.getParent(),
                        "Czy na pewno usunąć produkt z zamówienia?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    produkty.remove(selectedRow);
                    model.removeRow(selectedRow);
                }
            }
        });

        pmenu.add(pusun);

        panel.add(pmenu);

        JPanel pBot = new JPanel();

        dodaj = new JButton("Dodaj Zamówienie");
        JButton anuluj = new JButton("Anuluj");

        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (produkty.isEmpty()) {
                    JOptionPane.showMessageDialog(okno, "Nie wybrałeś żadnego produktu", "Brak produktu", JOptionPane.WARNING_MESSAGE);
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


        pBot.add(anuluj);
        pBot.add(dodaj);

        panel.add(pBot);


        getContentPane().add(BorderLayout.CENTER, panel);
    }
}
