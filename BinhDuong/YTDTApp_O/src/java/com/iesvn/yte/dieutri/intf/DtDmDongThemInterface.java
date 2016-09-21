/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmDongThem;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmDongThemInterface {

    public void create(DtDmDongThem dtDmDongThem);

    public void edit(DtDmDongThem dtDmDongThem);

    public void remove(DtDmDongThem dtDmDongThem);

    public DtDmDongThem find(Object id);

    public List<DtDmDongThem> findAll();
}
