/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDiaDiem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDiaDiemFacadeLocal {

    void create(DmDiaDiem dmDiaDiem);

    void edit(DmDiaDiem dmDiaDiem);

    void remove(DmDiaDiem dmDiaDiem);

    DmDiaDiem find(Object id);

    List<DmDiaDiem> findAll();

}


