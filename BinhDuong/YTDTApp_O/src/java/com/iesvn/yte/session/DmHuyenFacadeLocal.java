/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHuyen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmHuyenFacadeLocal {

    void create(DmHuyen dmHuyen);

    void edit(DmHuyen dmHuyen);

    void remove(DmHuyen dmHuyen);

    DmHuyen find(Object id);

    List<DmHuyen> findAll();

}


