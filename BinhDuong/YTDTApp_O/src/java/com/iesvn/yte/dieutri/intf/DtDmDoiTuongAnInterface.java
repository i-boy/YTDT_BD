/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmDoiTuongAn;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmDoiTuongAnInterface {

    public void create(DtDmDoiTuongAn dtDmDoiTuongAn);

    public void edit(DtDmDoiTuongAn dtDmDoiTuongAn);

    public void remove(DtDmDoiTuongAn dtDmDoiTuongAn);

    public DtDmDoiTuongAn find(Object id);

    public List<DtDmDoiTuongAn> findAll();
}
