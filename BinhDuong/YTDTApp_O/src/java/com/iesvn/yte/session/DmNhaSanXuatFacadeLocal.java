/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhaSanXuat;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNhaSanXuatFacadeLocal {

    void create(DmNhaSanXuat dmNhaSanXuat);

    void edit(DmNhaSanXuat dmNhaSanXuat);

    void remove(DmNhaSanXuat dmNhaSanXuat);

    DmNhaSanXuat find(Object id);

    List<DmNhaSanXuat> findAll();

    public java.util.List<com.iesvn.yte.entity.DmNhaSanXuat> findByDmThuocMaAndDmQuocGiaMa(java.lang.String dmtMa, java.lang.Integer dmqgMaso);

}


