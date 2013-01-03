/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import baza.MyDB;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import okienka.DodajProduktOkno;

/**
 *
 * @author taksun
 */
public class StanMagazyn extends Stan {

    int selectedRow = -1;
    MyDB baza = MyDB.getInstance();
    JPanel panel;

    @Override
    public void setPanel(JPanel p) {

        panel = p;

        p.removeAll();

        FlowLayout experimentLayout = new FlowLayout();
        p.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.LEADING);

        String[] col = {"ID",
            "Nazwa",
            "Kategoria",
            "Ilość",
            "Cena",
            "VAT"};

        Object[][] data = baza.getProdukty();

        final DefaultTableModel model = new DefaultTableModel(data, col);


        JTable table = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };

        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
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
        p.add(scrollPane);


        JButton btnAdd = new JButton("Dodaj");

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DodajProduktOkno okno = new DodajProduktOkno();
                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);

                if (okno.dodane) {
                    baza.addProdukt(okno.nazwa.getText(),
                            baza.getKategoria(okno.kategoria.getSelectedIndex()),
                            Integer.parseInt(okno.ilosc.getText()),
                            Float.parseFloat(okno.cena.getText()),
                            Float.parseFloat(okno.cena.getText()) * 4,
                            Float.parseFloat((String) okno.vat.getSelectedItem()) / 100);
                    model.addRow(baza.getLastProdukt());
                }
            }
        });

        p.add(btnAdd);

        JButton btnEdit = new JButton("Edytuj");

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    return;
                }

                
            }
        });

        p.add(btnEdit);
        
        JButton btnDel = new JButton("Usuń");

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        panel.getParent(),
                        "Czy na pewno usunąć produkt?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    baza.removeProdukt(selectedRow);
                    model.removeRow(selectedRow);
                }
            }
        });

        p.add(btnDel);

        //Left to right component orientation is selected by default
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        p.validate();
        p.repaint();
    }
}
