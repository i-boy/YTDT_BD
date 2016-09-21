/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtNhapKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtNhapKhoInterface {

public     void create(CtNhapKho ctNhapKho);

public     void edit(CtNhapKho ctNhapKho);

public     void remove(CtNhapKho ctNhapKho);

public     CtNhapKho find(Object id);

public     List<CtNhapKho> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.CtNhapKho> findByPhieuNhapKho(java.lang.String pnkMa);

}


