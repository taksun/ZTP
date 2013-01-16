/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package firma;

/**
 *
 * @author taksun
 */
public class Ustawienia {

    private float kurs_euro;
    private String miejscowosc = "Bialystok";
    private String nazwa = "Nasza piekna firma";
    private String kod = "15-001";
    private String adres = "ul. Wiejska 45A";
    private String nip = "1234567890";
    private String regon = "987654321";
    
    private static Ustawienia singleton;
    
    private Observer obs = new Observer(this);

    private Ustawienia() {
    }

    public static Ustawienia getInstance() {
        if (singleton == null) {
            singleton = new Ustawienia();
        }
        return singleton;
    }

    public Memento stworzMemento() {
        return new MementoUstawien().setKurs(kurs_euro);
    }

    public void przywroc(Memento m) {
        kurs_euro = ((MementoUstawien) m).getKurs();
    }

    public void setKurs(float k) {
        kurs_euro = k;

        notyfy();
    }

    public float getKurs() {
        return kurs_euro;
    }
    
    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getKod() {
        return kod;
    }

    public String getAdres() {
        return adres;
    }

    public String getNip() {
        return nip;
    }

    public String getRegon() {
        return regon;
    }
    
    private void notyfy() {
        obs.update();
    }

    private static class MementoUstawien implements Memento {

        private float kurs;

        Memento setKurs(float k) {
            kurs = k;
            return this;
        }

        public float getKurs() {
            return kurs;
        }
    }
}
