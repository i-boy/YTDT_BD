/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhTruyenNhiem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmBenhTruyenNhiemFacadeLocal {

    void create(DmBenhTruyenNhiem dmBenhTruyenNhiem);

    void edit(DmBenhTruyenNhiem dmBenhTruyenNhiem);

    void remove(DmBenhTruyenNhiem dmBenhTruyenNhiem);

    DmBenhTruyenNhiem find(Object id);

    List<DmBenhTruyenNhiem> findAll();

}


