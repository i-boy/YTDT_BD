/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomHocVi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNhomHocViFacadeLocal {

    void create(DmNhomHocVi dmNhomHocVi);

    void edit(DmNhomHocVi dmNhomHocVi);

    void remove(DmNhomHocVi dmNhomHocVi);

    DmNhomHocVi find(Object id);

    List<DmNhomHocVi> findAll();

}


