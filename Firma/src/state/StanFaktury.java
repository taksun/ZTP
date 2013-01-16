/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import baza.Faktura;
import baza.MyDB;
import builder.HTMLBuilder;
import builder.PDFBuilder;
import builder.Writer;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import okienka.DodajFaktureOkno;

/**
 *
 * @author taksun
 */
public class StanFaktury extends Stan {

    int selectedRow = -1;
    MyDB baza = MyDB.getInstance();
    JPanel panel;

    @Override
    public void setPanel(JPanel p) {

        panel = p;

        p.removeAll();

        p.setLayout(new BorderLayout());
        
        JPanel pTop = new JPanel();
        JLabel lbl = new JLabel("Faktury");
        
        pTop.add(lbl);
        
        p.add(pTop, BorderLayout.PAGE_START);

        String[] col = {"ID",
            "Data wystawienia faktury",
            "ID zamowienia",
            "Oplacona"};

        Object[][] data = baza.getFaktury();

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

                DodajFaktureOkno okno = new DodajFaktureOkno();

                if (okno.pusto) {
                    JOptionPane.showMessageDialog(null, "W bazie nie ma dostepnych zamówień dla których można wystawić fakturę.", "Brak dostepnych zamowien", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                okno.setLocationRelativeTo(panel);
                okno.setVisible(true);

                if (okno.dodane) {

                    baza.addFaktura(okno.alist.get(okno.zamowienia.getSelectedIndex()).getID());
                    okno.alist.get(okno.zamowienia.getSelectedIndex()).setFakID((int) baza.getLastFaktura()[0]);
                    model.addRow(baza.getLastFaktura());
                }
            }
        });

        pBot.add(btnAdd);

        JButton btnStatus = new JButton("Zmień oplacone");

        btnStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś faktury!", "Brak wybranej faktury", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                baza.getFaktura(selectedRow).changeOplacone();
                model.setValueAt(!(boolean) model.getValueAt(selectedRow, 3), selectedRow, 3);

            }
        });

        pBot.add(btnStatus);
        
        JButton btnBuildPDF = new JButton("Utworz PDF");

        btnBuildPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś faktury!", "Brak wybranej faktury", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Faktura f = baza.getFaktura(selectedRow);
                Writer w = new Writer(new PDFBuilder("Faktura nr " + Integer.toString(f.getID())));
                w.construct(f);

            }
        });

        pBot.add(btnBuildPDF);
        
        JButton btnBuildHTML = new JButton("Utworz HTML");

        btnBuildHTML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś faktury!", "Brak wybranej faktury", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Faktura f = baza.getFaktura(selectedRow);
                Writer w = new Writer(new HTMLBuilder("Faktura nr " + Integer.toString(f.getID())));
                w.construct(f);

            }
        });

        pBot.add(btnBuildHTML);

        JButton btnDel = new JButton("Usuń");

        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (selectedRow < 0) {
                    JOptionPane.showMessageDialog(panel, "Nie wybraleś faktury!", "Brak wybranej faktury", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int n = JOptionPane.showConfirmDialog(
                        panel.getParent(),
                        "Czy na pewno usunąć fakturę?",
                        "Usuwanie",
                        JOptionPane.YES_NO_OPTION);

                if (n == JOptionPane.YES_OPTION) {
                    baza.removeZamowienie(selectedRow);
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
