/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

import java.util.Date;

/**
 *
 * @author Karolina
 */
public class Zamowienie {

    
    private int zamID;
    private Date data;
    private String status;// nie wiem czy tak, bo w sumie chyba mozna inta i zrobic 0-niezrealizowane, 1-czeka,2-zrealizowane.. dokładnie nie wiem

    public Zamowienie(int zID, String d, String s) {
        zamID = zID;
        data = new Date();
        status = s;
    }
    
}
