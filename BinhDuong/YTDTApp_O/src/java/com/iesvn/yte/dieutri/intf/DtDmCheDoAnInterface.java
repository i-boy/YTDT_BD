/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmCheDoAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmCheDoAnInterface {

    public void create(DtDmCheDoAn dtDmCheDoAn);

    public void edit(DtDmCheDoAn dtDmCheDoAn);

    public void remove(DtDmCheDoAn dtDmCheDoAn);

    public DtDmCheDoAn find(Object id);

    public List<DtDmCheDoAn> findAll();
}
