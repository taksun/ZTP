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
    private String miejscowosc;
    private String nazwa;
    private String kod;
    private String adres;
    private String nip;
    private String regon;
    private static Ustawienia singleton;
    private Observer obs = new ObserverKurs(this);

    private Ustawienia() {
    }

    public static Ustawienia getInstance() {
        if (singleton == null) {
            singleton = new Ustawienia();
        }
        return singleton;
    }

    public Memento stworzMemento() {
        return new MementoUstawien().setDane(kurs_euro, miejscowosc, nazwa, kod, adres, nip, regon);
    }

    public void przywroc(Memento m) {
        MementoUstawien mem = (MementoUstawien) m;
        kurs_euro = mem.getKurs();
        miejscowosc = mem.getMiejscowosc();
        nazwa = mem.getNazwa();
        kod = mem.getKod();
        adres = mem.getAdres();
        nip = mem.getNip();
        regon = mem.getRegon();
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

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }

    public String getDaneFirmy() {
        return adres + "\n" + kod + " " + miejscowosc + "\nNIP: " + nip + "  Regon: " + regon;
    }

    private void notyfy() {
        obs.update();
    }

    private static class MementoUstawien implements Memento {

        private float kurs;
        private String miejscowosc;
        private String nazwa;
        private String kod;
        private String adres;
        private String nip;
        private String regon;

        Memento setDane(float k, String msc, String n, String ko, String a, String ni, String r) {
            kurs = k;
            miejscowosc = msc;
            nazwa = n;
            kod = ko;
            adres = a;
            nip = ni;
            regon = r;
            return this;
        }

        public float getKurs() {
            return kurs;
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
        
    }
}
