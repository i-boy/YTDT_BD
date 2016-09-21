/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDonVi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDonViFacadeLocal {

    void create(DmDonVi dmDonVi);

    void edit(DmDonVi dmDonVi);

    void remove(DmDonVi dmDonVi);

    DmDonVi find(Object id);

    List<DmDonVi> findAll();

}


