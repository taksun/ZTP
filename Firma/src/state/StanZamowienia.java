/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import baza.Produkt;
import baza.Proxy;
import baza.Zamowienie;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import okienka.DodajZamowienieOkno;

/**
 *
 * @author taksun
 */
public class StanZamowienia extends Stan {

    int selectedRow = -1;
    Proxy prox = new Proxy();
    JPanel panel;

    @Override
    public void setPanel(JPanel p) {

        panel = p;

        p.removeAll();

        p.setLayout(new BorderLayout());

        JPanel pTop = new JPanel();
        JLabel lbl = new JLabel("Zamowienia");

        pTop.add(lbl);

        p.add(pTop, BorderLayout.PAGE_START);

        String[] col = {"ID",
            "Data",
            "Status",
            "Klient",
            "Cena w euro"};

        Object[][] data = prox.getZamowienia();

        final DefaultTableModel model = new DefaultTableModel(data, col);

        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };

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
        p.add(scrollPane, BorderLayout.CENTER);

        JPanel pBot = new JPanel();

        JButton btnAdd = new JButton("Dodaj");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DodajZamowienieOkno okno = new DodajZamowienieOkno(new ArrayList<Produkt>(), new ArrayList<Produkt>());

                if (okno.error) {
                    return;
                }

                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);

                if (okno.dodane) {

                    prox.addZamowienie(prox.getKlientID(okno.klient.getSelectedIndex()), okno.produkty, okno.euro.isSelected());
                    model.addRow(prox.getLastZamowienie());

                }
            }
        });

        pBot.add(btnAdd);

        JButton btnEdit = new JButton("Edytuj");

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś produktu!", "Brak wybranego produktu", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Zamowienie z = prox.getZamowienie(selectedRow);

                ArrayList<Produkt> al = new ArrayList<>();

                for (Produkt item : z.getProdukty()) {
                    al.add(new Produkt(item));
                }

                DodajZamowienieOkno okno = new DodajZamowienieOkno(al, z.getProdukty());

                okno.setTitle("Edytuj zamówienie nr: " + Integer.toString(z.getID()));
                okno.dodaj.setText("Zapisz");

                okno.klient.setSelectedIndex(prox.getKlientRowByID(z.getKlientID()));
                okno.euro.setSelected(z.isEuro());

                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);

                if (okno.dodane) {
                    prox.editZamowienie(z.getID(), prox.getKlientID(okno.klient.getSelectedIndex()), okno.produkty, okno.euro.isSelected());
                    model.setValueAt(prox.getKlient(okno.klient.getSelectedIndex()), selectedRow, 3);
                    String c = "Nie";
                    if (okno.euro.isSelected()) {
                        c = "Tak";
                    }
                    model.setValueAt(c, selectedRow, 4);
                }
            }
        });

        pBot.add(btnEdit);

        JButton btnStatus = new JButton("Zmień status");

        btnStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś zamówienia!", "Brak wybranego zamówienia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Object[] possibilities = {"Nowe", "W trakcie realizacji", "Zrealizowane"};
                String s = (String) JOptionPane.showInputDialog(
                        null,
                        "Wybierz status zamówienia",
                        "Zmiana statusu",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilities,
                        prox.getZamowienie(selectedRow).getStatus());

                if ((s != null) && (s.length() > 0)) {
                    prox.getZamowienie(selectedRow).setStatus(s);
                    model.setValueAt(s, selectedRow, 2);
                }
            }
        });

        pBot.add(btnStatus);

        JButton btnDel = new JButton("Usuń");

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś zamówienia!", "Brak wybranego zamówienia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        panel.getParent(),
                        "Czy na pewno usunąć zamówienie?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    prox.removeZamowienie(selectedRow);
                    model.removeRow(selectedRow);
                }
            }
        });

        pBot.add(btnDel);

        FlowLayout experimentLayout = new FlowLayout();
        pBot.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);

        //Left to right component orientation is selected by default
        pBot.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        p.add(pBot, BorderLayout.PAGE_END);

        p.validate();
        p.repaint();
    }
}
