/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhaSxSpdd;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmNhaSxSpddInterface {

    public void create(DtDmNhaSxSpdd dtDmNhaSxSpdd);

    public void edit(DtDmNhaSxSpdd dtDmNhaSxSpdd);

    public void remove(DtDmNhaSxSpdd dtDmNhaSxSpdd);

    public DtDmNhaSxSpdd find(Object id);

    public List<DtDmNhaSxSpdd> findAll();
}
