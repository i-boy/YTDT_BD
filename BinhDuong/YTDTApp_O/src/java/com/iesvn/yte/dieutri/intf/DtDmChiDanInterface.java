/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmChiDan;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmChiDanInterface {

public     void create(DtDmChiDan dtDmChiDan);

public     void edit(DtDmChiDan dtDmChiDan);

public     void remove(DtDmChiDan dtDmChiDan);

public     DtDmChiDan find(Object id);

public     List<DtDmChiDan> findAll();

public DtDmChiDan findByMa(String maChiDan) ;

}


