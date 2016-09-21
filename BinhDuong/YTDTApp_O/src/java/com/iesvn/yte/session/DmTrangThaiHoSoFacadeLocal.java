/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTrangThaiHoSo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTrangThaiHoSoFacadeLocal {

    void create(DmTrangThaiHoSo dmTrangThaiHoSo);

    void edit(DmTrangThaiHoSo dmTrangThaiHoSo);

    void remove(DmTrangThaiHoSo dmTrangThaiHoSo);

    DmTrangThaiHoSo find(Object id);

    List<DmTrangThaiHoSo> findAll();

}


