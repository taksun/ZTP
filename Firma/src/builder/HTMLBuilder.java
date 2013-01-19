/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import baza.Faktura;
import baza.Klient;
import baza.MyDB;
import baza.Produkt;
import baza.Zamowienie;
import firma.Ustawienia;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author taksun
 */
public class HTMLBuilder implements Builder {

    StringBuilder page = new StringBuilder();
    String filename;
    Faktura f;
    Zamowienie z;
    Klient k;
    Ustawienia ust = Ustawienia.getInstance();
    MyDB baza = MyDB.getInstance();

    public HTMLBuilder(String f) {
        filename = f;
    }

    @Override
    public void buildHead() {
        page.append("<html>" + "<head>" + "<title>Faktura nr: ").append(Integer.toString(f.getID())).append("</title>"
                + "<style>"
                + "body, table {"
                + "font-family: 'Times New Roman',Arial;"
                + "font-size: 14px;"
                + "}"
                + "#tabela {"
                + "width: 900px;"
                + "}"
                + "#produkty {"
                + "width: 100%;"
                + "border-spacing: 0px;"
                + "border-collapse:collapse;"
                + "}"
                + "#produkty td {"
                + "border: 1px solid black;"
                + "padding: 2px;"
                + "}"
                + "#podpisy {"
                + "width: 90%;"
                + "margin-top: 200px;"
                + "margin-left: auto;"
                + "margin-right: auto;"
                + "}"
                + ".smallFont {"
                + "font-size: 11px;"
                + "}"
                + ".boldFont {"
                + "font-weight: bold;"
                + "}"
                + ".bigFont {"
                + "font-size: 26px;"
                + "}"
                + ".podpis {"
                + "border-top: 1px solid black;"
                + "font-size: 11px;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<table id='tabela'>");

        page.append("<tr>" + "<td align='right'>").append(ust.getMiejscowosc()).append(", data wystawienia: ").append(f.getDate()).append("<br />Data sprzedazy: ").append(f.getDate()).append("</td>"
                + "</tr>");

        String dane = ust.getDaneFirmy();
        dane = dane.replaceAll("\n", "<br />");

        page.append("<tr>" + "<td><span class='smallFont'>Sprzedawca:</span><hr />" + "<span class='boldFont'>").append(ust.getNazwa()).append("</span><br />").append(dane).append("<br /><br /><br /><br /><br /></td>"
                + "</tr>");

        z = baza.getZamowienieByID(f.getZamID());
        k = baza.getKlientByID(z.getKlientID());

        dane = k.getDane();
        dane = dane.replaceAll("\n", "<br />");

        page.append("<tr>" + "<td><span class='smallFont'>Nabywca:</span><hr />" + "<span class='boldFont'>").append(k.getNazwa()).append("</span><br />").append(dane).append("<br /><br /></td>"
                + "</tr>");

        page.append("<tr>" + "<td align='center' class='bigFont'>Faktura VAT ").append(Integer.toString(f.getID())).append("<br /><br /></td>"
                + "</tr>");
    }

    @Override
    public void buildBody() {
        String waluta = "z.gr";
        if (z.isEuro()) {
            waluta = "Euro";
        }
        page.append("<tr>" + "<td>" + "<table id='produkty'>" + "<tr>" + "<td class='smallFont' align='center'>Lp.</td>" + "<td class='smallFont' align='center'>Nazwa towaru/uslugi</td>" + "<td class='smallFont' align='center'>Stawka<br />VAT</td>" + "<td class='smallFont' align='center'>Ilosc</td>" + "<td class='smallFont' align='center'>Cena<br />jednostkowa<br />bez podatku<br />[").append(waluta).append("]</td>" + "<td class='smallFont' align='center'>Wartosc<br />bez podatku<br />[").append(waluta).append("]</td>" + "<td class='smallFont' align='center'>Kwota<br />VAT</td>" + "<td class='smallFont' align='center'>Wartosc<br />z podatkiem<br />[").append(waluta).append("]</td>"
                + "</tr>");

        float suma = 0.0F;
        float sumavat = 0.0F;

        for (Produkt item : z.getProdukty()) {
            page.append("<tr><td align='right'>");
            page.append(Integer.toString(item.getID()));
            page.append("</td><td style='width: 400px'>");
            page.append(item.getNazwa());
            page.append("</td><td align='center'>");

            float vat = item.getVAT();

            page.append(Integer.toString((int) (vat * 100))).append("%");
            page.append("</td><td align='right'>");

            int ilosc = item.getIlosc();

            page.append(Integer.toString(ilosc));
            page.append("</td><td align='right'>");

            float cena;

            if (z.isEuro()) {
                cena = item.getCena_euro();
            } else {
                cena = item.getCena();
            }

            page.append(Float.toString(cena));
            page.append("</td><td align='right'>");

            float cenarazem = cena * ilosc;
            cenarazem *= 100;
            cenarazem = Math.round(cenarazem);
            cenarazem /= 100;
            
            suma += cenarazem;

            page.append(Float.toString(cenarazem));
            page.append("</td><td align='right'>");

            vat = cenarazem * vat;
            
            vat *= 100;
            vat = Math.round(vat);
            vat /= 100;

            sumavat += vat;

            page.append(Float.toString(vat));
            page.append("</td><td align='right'>");
            
            float cenazvat = cenarazem + vat;

            cenazvat *= 100;
            cenazvat = Math.round(cenazvat);
            cenazvat /= 100;
            
            page.append(Float.toString(cenazvat));
            page.append("</td><tr>");
        }
        
        float cenazvat = suma + sumavat;

        cenazvat *= 100;
        cenazvat = Math.round(cenazvat);
        cenazvat /= 100;

        page.append("<tr>" + "<td colspan='4' style='border: 0px;'></td>" + "<td align='right' style='border: 0px;'>Razem:</td>" + "<td align='right'>").append(Float.toString(suma)).append("</td>" + "<td align='right'>").append(Float.toString(sumavat)).append("</td>" + "<td align='right'>").append(Float.toString(cenazvat)).append("</td>"
                + "</td>");

        page.append("</table>"
                + "</td>"
                + "</tr>");
    }

    @Override
    public void buildFoot() {

        page.append("<tr>"
                + "<td>"
                + "<table id='podpisy'>"
                + "<tr>"
                + "<td align='center' class='podpis'>podpis osoby uprawnionej do odbioru faktury VAT</td>"
                + "<td style='width: 100px'></td>"
                + "<td align='center' class='podpis'>podpis osoby uprawnionej do wystawienia faktury VAT</td>"
                + "</table>"
                + "</td>"
                + "</td>");

        page.append("</table>"
                + "</body>"
                + "</html>");
    }

    @Override
    public void SaveFile() {
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter("./faktury/" + filename + ".html"))) {
                out.write(page.toString());
            }
        } catch (IOException ex) {
            System.out.println("Exception " + ex.getMessage());
        }

    }

    @Override
    public void setFaktura(Faktura _f) {
        f = _f;
    }
}
