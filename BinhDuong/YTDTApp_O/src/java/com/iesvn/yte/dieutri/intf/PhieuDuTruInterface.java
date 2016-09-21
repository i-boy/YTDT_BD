/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuDuTruInterface {

    public void create(PhieuDuTru phieuDuTru);

    public void edit(PhieuDuTru phieuDuTru);

    public void remove(PhieuDuTru phieuDuTru);

    public PhieuDuTru find(Object id);

    public List<PhieuDuTru> findAll();
    
    public boolean removeAllPhieuDuTru(PhieuDuTru phieuGiaoBan);
}


