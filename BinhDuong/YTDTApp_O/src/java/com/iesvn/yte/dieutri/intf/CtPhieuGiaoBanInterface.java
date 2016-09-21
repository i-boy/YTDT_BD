/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtPhieuGiaoBan;
import java.util.List;

/**
 *
 * @author james
 */
public interface CtPhieuGiaoBanInterface {

    public void create(CtPhieuGiaoBan ctPhieuGiaoBan);

    public void edit(CtPhieuGiaoBan ctPhieuGiaoBan);

    public void remove(CtPhieuGiaoBan ctPhieuGiaoBan);

    public CtPhieuGiaoBan find(Object id);

    public List<CtPhieuGiaoBan> findAll();

    public List<CtPhieuGiaoBan> findByPhieuGiaoBan(String maPhieu);
}
