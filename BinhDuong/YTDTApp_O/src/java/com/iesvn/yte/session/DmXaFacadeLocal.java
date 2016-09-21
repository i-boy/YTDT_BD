/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmXa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmXaFacadeLocal {

    void create(DmXa dmXa);

    void edit(DmXa dmXa);

    void remove(DmXa dmXa);

    DmXa find(Object id);

    List<DmXa> findAll();

}


