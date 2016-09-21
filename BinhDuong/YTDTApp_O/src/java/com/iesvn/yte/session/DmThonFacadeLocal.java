/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmThon;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmThonFacadeLocal {

    void create(DmThon dmThon);

    void edit(DmThon dmThon);

    void remove(DmThon dmThon);

    DmThon find(Object id);

    List<DmThon> findAll();

}


