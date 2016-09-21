/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLoaiMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmLoaiMienInterface {

public     void create(DtDmLoaiMien dtDmLoaiMien);

public     void edit(DtDmLoaiMien dtDmLoaiMien);

public     void remove(DtDmLoaiMien dtDmLoaiMien);

public     DtDmLoaiMien find(Object id);

public     List<DtDmLoaiMien> findAll();

}


