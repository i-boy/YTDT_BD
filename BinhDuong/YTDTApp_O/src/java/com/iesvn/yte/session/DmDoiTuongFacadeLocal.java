/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDoiTuong;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDoiTuongFacadeLocal {

    void create(DmDoiTuong dmDoiTuong);

    void edit(DmDoiTuong dmDoiTuong);

    void remove(DmDoiTuong dmDoiTuong);

    DmDoiTuong find(Object id);

    List<DmDoiTuong> findAll();

}


