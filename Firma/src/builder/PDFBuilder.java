/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import baza.Faktura;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.CMYKColor;
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
    Document document;
    PdfWriter writer;
    Ustawienia ust = Ustawienia.getInstance();
    private Font NormalFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL, BaseColor.BLACK);
    private Font SmallFont = new Font(Font.FontFamily.TIMES_ROMAN, 6, Font.NORMAL, BaseColor.BLACK);

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

        try {
            document.add(date);
            document.add(sprz);
            LineSeparator ls = new LineSeparator();
            //document.add(ls);
            document.add(Chunk.NEWLINE);
            document.add(ls);
        } catch (DocumentException ex) {
            Logger.getLogger(PDFBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void buildBody() {
    }

    @Override
    public void buildFoot() {
        Paragraph title1 = new Paragraph("Faktura nr : " + Integer.toString(f.getID()),
                FontFactory.getFont(FontFactory.HELVETICA,
                18, Font.BOLDITALIC, new CMYKColor(0, 255, 255, 17)));

        Chapter chapter1 = new Chapter(title1, 1);

        chapter1.setNumberDepth(0);

        Paragraph title11 = new Paragraph("This is Section 1 in Chapter 1",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                new CMYKColor(0, 255, 255, 17)));

        Section section1 = chapter1.addSection(title11);

        Paragraph someSectionText = new Paragraph(
                "This text comes as part of section 1 of chapter 1.");

        section1.add(someSectionText);

        someSectionText = new Paragraph("Following is a 3 X 2 table.");

        section1.add(someSectionText);

        PdfPTable t = new PdfPTable(3);

        t.setSpacingBefore(25);

        t.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(new Phrase("Header1"));

        t.addCell(c1);

        PdfPCell c2 = new PdfPCell(new Phrase("Header2"));

        t.addCell(c2);

        PdfPCell c3 = new PdfPCell(new Phrase("Header3"));

        t.addCell(c3);

        t.addCell("1.1");

        t.addCell("1.2");

        t.addCell("1.3");

        section1.add(t);

        List l = new List(true, false, 10);

        l.add(new ListItem("First item of list"));

        l.add(new ListItem("Second item of list"));

        section1.add(l);

        Paragraph title2 = new Paragraph("Using Anchor",
                FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD,
                new CMYKColor(0, 255, 0, 0)));

        section1.add(title2);

        try {
            document.add(chapter1);
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
