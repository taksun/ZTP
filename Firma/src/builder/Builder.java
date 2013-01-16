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
public interface Builder {
    void setFaktura(Faktura _f);
    void buildHead();
    void buildBody();
    void buildFoot();
    void SaveFile();
}
