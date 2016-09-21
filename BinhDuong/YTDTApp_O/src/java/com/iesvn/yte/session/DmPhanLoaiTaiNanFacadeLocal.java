/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhanLoaiTaiNan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmPhanLoaiTaiNanFacadeLocal {

    void create(DmPhanLoaiTaiNan dmPhanLoaiTaiNan);

    void edit(DmPhanLoaiTaiNan dmPhanLoaiTaiNan);

    void remove(DmPhanLoaiTaiNan dmPhanLoaiTaiNan);

    DmPhanLoaiTaiNan find(Object id);

    List<DmPhanLoaiTaiNan> findAll();

}


