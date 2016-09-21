/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTaiNan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTaiNanFacadeLocal {

    void create(DmTaiNan dmTaiNan);

    void edit(DmTaiNan dmTaiNan);

    void remove(DmTaiNan dmTaiNan);

    DmTaiNan find(Object id);

    List<DmTaiNan> findAll();

}


