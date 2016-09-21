/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmTinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmTinhFacadeLocal {

    void create(DmTinh dmTinh);

    void edit(DmTinh dmTinh);

    void remove(DmTinh dmTinh);

    DmTinh find(Object id);

    List<DmTinh> findAll();

}


