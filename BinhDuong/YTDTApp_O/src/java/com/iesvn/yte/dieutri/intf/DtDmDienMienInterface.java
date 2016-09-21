/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmDienMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmDienMienInterface {

public     void create(DtDmDienMien dtDmDienMien);

public     void edit(DtDmDienMien dtDmDienMien);

public     void remove(DtDmDienMien dtDmDienMien);

public     DtDmDienMien find(Object id);

public     List<DtDmDienMien> findAll();

}


