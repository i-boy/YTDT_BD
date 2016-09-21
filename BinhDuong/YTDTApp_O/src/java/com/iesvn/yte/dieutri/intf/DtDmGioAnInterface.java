/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmGioAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmGioAnInterface {

    public void create(DtDmGioAn dtDmGioAn);

    public void edit(DtDmGioAn dtDmGioAn);

    public void remove(DtDmGioAn dtDmGioAn);

    public DtDmGioAn find(Object id);

    public List<DtDmGioAn> findAll();
}
