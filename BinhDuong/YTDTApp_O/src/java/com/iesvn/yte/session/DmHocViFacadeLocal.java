/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHocVi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmHocViFacadeLocal {

    void create(DmHocVi dmHocVi);

    void edit(DmHocVi dmHocVi);

    void remove(DmHocVi dmHocVi);

    DmHocVi find(Object id);

    List<DmHocVi> findAll();

}


