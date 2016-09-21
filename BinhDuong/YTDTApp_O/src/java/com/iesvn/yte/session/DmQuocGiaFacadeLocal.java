/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuocGia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmQuocGiaFacadeLocal {

    void create(DmQuocGia dmQuocGia);

    void edit(DmQuocGia dmQuocGia);

    void remove(DmQuocGia dmQuocGia);

    DmQuocGia find(Object id);

    List<DmQuocGia> findAll();

}


