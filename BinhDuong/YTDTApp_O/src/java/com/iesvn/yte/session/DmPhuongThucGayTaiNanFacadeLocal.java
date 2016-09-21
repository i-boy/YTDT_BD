/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmPhuongThucGayTaiNan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmPhuongThucGayTaiNanFacadeLocal {

    void create(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan);

    void edit(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan);

    void remove(DmPhuongThucGayTaiNan dmPhuongThucGayTaiNan);

    DmPhuongThucGayTaiNan find(Object id);

    List<DmPhuongThucGayTaiNan> findAll();

}


