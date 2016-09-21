/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmBuong;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmBuongInterface {

    public void create(DtDmBuong dtDmBuong);

    public void edit(DtDmBuong dtDmBuong);

    public void remove(DtDmBuong dtDmBuong);

    public DtDmBuong find(Object id);

    public List<DtDmBuong> findAll();
}
