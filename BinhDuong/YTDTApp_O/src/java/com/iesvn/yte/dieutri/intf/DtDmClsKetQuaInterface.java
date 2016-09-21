/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmClsKetQua;
import java.util.List;

/**
 *
 * @author Thanh
 */
public interface DtDmClsKetQuaInterface {
    
    public void create(DtDmClsKetQua dtDmClsKetQua);

    public void edit(DtDmClsKetQua dtDmClsKetQua);

    public void remove(DtDmClsKetQua dtDmClsKetQua);

    public DtDmClsKetQua find(Object id);

    public List<DtDmClsKetQua> findAll();
    
    public List<DtDmClsKetQua> findByAllConditions(String ma, String ten, Integer dtdmclsbgMaso);

}
