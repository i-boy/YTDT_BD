/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmTuyenKcb;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmTuyenKcbInterface {

public     void create(DtDmTuyenKcb dtDmTuyenKcb);

public     void edit(DtDmTuyenKcb dtDmTuyenKcb);

public     void remove(DtDmTuyenKcb dtDmTuyenKcb);

public     DtDmTuyenKcb find(Object id);

public     List<DtDmTuyenKcb> findAll();

}


