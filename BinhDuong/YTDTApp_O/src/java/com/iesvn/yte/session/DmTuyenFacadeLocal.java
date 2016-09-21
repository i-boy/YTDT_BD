/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTuyen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTuyenFacadeLocal {

    void create(DmTuyen dmTuyen);

    void edit(DmTuyen dmTuyen);

    void remove(DmTuyen dmTuyen);

    DmTuyen find(Object id);

    List<DmTuyen> findAll();

}


