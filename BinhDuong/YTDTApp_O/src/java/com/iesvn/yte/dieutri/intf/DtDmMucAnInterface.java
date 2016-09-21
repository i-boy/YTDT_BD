/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmMucAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmMucAnInterface {

    public void create(DtDmMucAn dtDmMucAn);

    public void edit(DtDmMucAn dtDmMucAn);

    public void remove(DtDmMucAn dtDmMucAn);

    public DtDmMucAn find(Object id);

    public List<DtDmMucAn> findAll();
}
