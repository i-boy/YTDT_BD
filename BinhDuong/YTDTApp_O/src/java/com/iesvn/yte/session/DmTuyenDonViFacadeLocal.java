/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTuyenDonVi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTuyenDonViFacadeLocal {

    void create(DmTuyenDonVi dmTuyenDonVi);

    void edit(DmTuyenDonVi dmTuyenDonVi);

    void remove(DmTuyenDonVi dmTuyenDonVi);

    DmTuyenDonVi find(Object id);

    List<DmTuyenDonVi> findAll();

}


