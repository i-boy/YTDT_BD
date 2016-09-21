/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmCls;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmClsInterface {

    public void create(DtDmCls dtDmCls);

    public void edit(DtDmCls dtDmCls);

    public void remove(DtDmCls dtDmCls);

    public DtDmCls find(Object id);

    public List<DtDmCls> findAll();
    
    public List<DmKhoa> getListDmKhoa(Integer clsMaso);
}


