/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPbCls;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmPbClsInterface {

public     void create(DtDmPbCls dtDmPbCls);

public     void edit(DtDmPbCls dtDmPbCls);

public     void remove(DtDmPbCls dtDmPbCls);

public     DtDmPbCls find(Object id);

public     List<DtDmPbCls> findAll();

}


