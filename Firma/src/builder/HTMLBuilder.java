/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import baza.Faktura;
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

    public HTMLBuilder(String f) {
        filename = f;
    }

    @Override
    public void buildHead() {
        page.append("<html>" + "<head>" + "<title>Faktura nr: ").append(Integer.toString(f.getID())).append("</title>"
                + "</head>"
                + "<body>"
                + "costam");
    }

    @Override
    public void buildBody() {
        page.append("asd");
    }

    @Override
    public void buildFoot() {
        page.append("</body>"
                + "</html>");
    }

    @Override
    public void SaveFile() {
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter("./faktury/"+filename+".html"))) {
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
