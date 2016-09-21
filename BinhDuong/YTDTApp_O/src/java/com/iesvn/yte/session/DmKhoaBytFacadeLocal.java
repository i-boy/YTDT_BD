/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKhoaByt;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmKhoaBytFacadeLocal {

    void create(DmKhoaByt dmKhoaByt);

    void edit(DmKhoaByt dmKhoaByt);

    void remove(DmKhoaByt dmKhoaByt);

    DmKhoaByt find(Object id);

    List<DmKhoaByt> findAll();

}


