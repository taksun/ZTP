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
