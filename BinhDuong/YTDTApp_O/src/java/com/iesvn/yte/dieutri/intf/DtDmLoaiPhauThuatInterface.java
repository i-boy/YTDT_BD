/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmLoaiPhauThuatInterface {

public     void create(DtDmLoaiPhauThuat dtDmLoaiPhauThuat);

public     void edit(DtDmLoaiPhauThuat dtDmLoaiPhauThuat);

public     void remove(DtDmLoaiPhauThuat dtDmLoaiPhauThuat);

public     DtDmLoaiPhauThuat find(Object id);

public     List<DtDmLoaiPhauThuat> findAll();

}


