/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import baza.Faktura;

/**
 *
 * @author taksun
 */
public class Writer {
    private Builder b;
    
    public Writer(Builder _b) {
        b = _b;
    }
    
    public void construct(Faktura f)
    {
        b.setFaktura(f);
        b.buildHead();
        b.buildBody();
        b.buildFoot();
        b.SaveFile();
    }
}
