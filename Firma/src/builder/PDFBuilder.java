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
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import firma.Ustawienia;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author taksun
 */
public class PDFBuilder implements Builder {

    Faktura f;
    Zamowienie z;
    Klient k;
    Document document;
    PdfWriter writer;
    Ustawienia ust = Ustawienia.getInstance();
    MyDB baza = MyDB.getInstance();
    private Font NormalFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
    private Font BigFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL, BaseColor.BLACK);
    private Font BoldFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD, BaseColor.BLACK);
    private Font SmallFont = new Font(Font.FontFamily.TIMES_ROMAN, 7, Font.NORMAL, BaseColor.BLACK);

    public PDFBuilder(String filename) {
        document = new Document(PageSize.A4, 20, 20, 20, 20);
        try {
            try {
                writer = PdfWriter.getInstance(document, new FileOutputStream("./faktury/" + filename + ".pdf"));
            } catch (DocumentException ex) {
                Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
        document.open();
    }

    @Override
    public void buildHead() {
        document.addTitle("Faktura nr: " + Integer.toString(f.getID()));
        document.addSubject("Faktura nr: " + Integer.toString(f.getID()));
        document.addKeywords("Faktura, " + Integer.toString(f.getID()) + ", Faktura nr " + Integer.toString(f.getID()));
        document.addAuthor("Firma");
        document.addCreator("Firma");

        Paragraph date = new Paragraph(ust.getMiejscowosc() + ", data wystawienia: " + f.getDate() + "\nData sprzedazy: " + f.getDate(), NormalFont);
        date.setAlignment(Element.ALIGN_RIGHT);

        Paragraph sprz = new Paragraph("Sprzedawca:", SmallFont);

        Paragraph sprze = new Paragraph(ust.getNazwa(), BoldFont);
        Paragraph sprzed = new Paragraph(ust.getDaneFirmy(), NormalFont);
        sprzed.setLeading(11);

        Paragraph nab = new Paragraph("Nabywca:", SmallFont);
        nab.setSpacingBefore(20);

        z = baza.getZamowienieByID(f.getZamID());
        k = baza.getKlientByID(z.getKlientID());

        Paragraph naby = new Paragraph(k.getNazwa(), BoldFont);
        Paragraph nabyw = new Paragraph(k.getDane(), NormalFont);
        sprzed.setLeading(11);
        sprzed.setSpacingAfter(30);

        Paragraph faktura = new Paragraph("Faktura VAT " + Integer.toString(f.getID()), BigFont);
        faktura.setAlignment(Element.ALIGN_CENTER);

        try {
            document.add(date);

            document.add(sprz);
            LineSeparator ls = new LineSeparator();
            ls.setOffset(-2);
            ls.setLineWidth(0.1F);
            document.add(ls);
            document.add(sprze);
            document.add(sprzed);

            document.add(nab);
            document.add(ls);
            document.add(naby);
            document.add(nabyw);

            document.add(faktura);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buildBody() {
        PdfPTable t = new PdfPTable(8);

        t.setWidthPercentage(100);

        float[] szerokosci = {30f, 300f, 40f, 60f, 70f, 70f, 70f, 70f};

        try {
            t.setWidths(szerokosci);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        t.setSpacingBefore(25);

        t.setSpacingAfter(25);

        t.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell c1 = new PdfPCell(new Phrase("Lp.", SmallFont));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Nazwa towaru/uslugi", SmallFont));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c2);

        PdfPCell c6 = new PdfPCell(new Phrase("Stawka \nVAT", SmallFont));
        c6.setHorizontalAlignment(Element.ALIGN_CENTER);
        c6.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c6);

        PdfPCell c3 = new PdfPCell(new Phrase("Ilosc", SmallFont));
        c3.setHorizontalAlignment(Element.ALIGN_CENTER);
        c3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c3);

        PdfPCell c4 = new PdfPCell(new Phrase("Cena jednostkowa \nbez podatku \n[zł.gr]", SmallFont));
        c4.setHorizontalAlignment(Element.ALIGN_CENTER);
        c4.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c4);

        PdfPCell c5 = new PdfPCell(new Phrase("Wartosc \nbez podatku \n[zł.gr]", SmallFont));
        c5.setHorizontalAlignment(Element.ALIGN_CENTER);
        c5.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c5);

        PdfPCell c7 = new PdfPCell(new Phrase("Kwota \nVAT", SmallFont));
        c7.setHorizontalAlignment(Element.ALIGN_CENTER);
        c7.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c7);

        PdfPCell c8 = new PdfPCell(new Phrase("Wartosc \nz podatkiem \n[zł.gr]", SmallFont));
        c8.setHorizontalAlignment(Element.ALIGN_CENTER);
        c8.setVerticalAlignment(Element.ALIGN_MIDDLE);

        t.addCell(c8);

        float suma = 0.0F;
        float sumavat = 0.0F;

        for (Produkt item : z.getProdukty()) {
            PdfPCell cell = new PdfPCell(new Phrase(Integer.toString(item.getID()), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);

            cell = new PdfPCell(new Phrase(item.getNazwa(), NormalFont));
            t.addCell(cell);

            float vat = item.getVAT();

            cell = new PdfPCell(new Phrase(Integer.toString((int) (vat * 100)) + "%", NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(cell);

            int ilosc = item.getIlosc();

            cell = new PdfPCell(new Phrase(Integer.toString(ilosc), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);

            float cena = item.getCena();

            cell = new PdfPCell(new Phrase(Float.toString(cena), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);

            float cenarazem = cena * ilosc;
            suma += cenarazem;

            cell = new PdfPCell(new Phrase(Float.toString(cenarazem), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);

            vat = cenarazem * vat;

            sumavat += vat;

            cell = new PdfPCell(new Phrase(Float.toString(vat), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);

            cell = new PdfPCell(new Phrase(Float.toString(cenarazem + vat), NormalFont));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            t.addCell(cell);
        }

        PdfPCell cell = new PdfPCell(new Phrase("", NormalFont));
        cell.setColspan(4);
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        cell = new PdfPCell(new Phrase("Razem:", NormalFont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        cell = new PdfPCell(new Phrase(Float.toString(suma), NormalFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        cell = new PdfPCell(new Phrase(Float.toString(sumavat), NormalFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        cell = new PdfPCell(new Phrase(Float.toString(suma + sumavat), NormalFont));
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        t.addCell(cell);

        try {
            document.add(t);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buildFoot() {
        PdfPTable t = new PdfPTable(3);

        t.setWidthPercentage(90);
        
        t.setSpacingBefore(100);
        
        t.getDefaultCell().setBorder(0);

        float[] szerokosci = {300f, 100f, 300f,};

        try {
            t.setWidths(szerokosci);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }

        LineSeparator ls = new LineSeparator();
        ls.setOffset(-3);
        ls.setLineWidth(0.1F);

        PdfPCell c1 = new PdfPCell();
        c1.setBorder(0);
        c1.addElement(ls);
        t.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("", NormalFont));
        c2.setBorder(0);
        t.addCell(c2);

        PdfPCell c3 = new PdfPCell();
        c3.setBorder(0);
        c3.addElement(ls);
        t.addCell(c3);
        
        PdfPCell cell = new PdfPCell(new Phrase("podpis osoby uprawnionej do odbioru faktury VAT", SmallFont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(cell);
        
        cell = new PdfPCell(new Phrase("", SmallFont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(cell);
        
        cell = new PdfPCell(new Phrase("podpis osoby uprawnionej do wystawienia faktury VAT", SmallFont));
        cell.setBorder(0);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        t.addCell(cell);

        try {
            document.add(t);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void SaveFile() {

        document.close();
    }

    @Override
    public void setFaktura(Faktura _f) {
        f = _f;
    }
}
