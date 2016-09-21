/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmHocHam;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmHocHamFacadeLocal {

    void create(DmHocHam dmHocHam);

    void edit(DmHocHam dmHocHam);

    void remove(DmHocHam dmHocHam);

    DmHocHam find(Object id);

    List<DmHocHam> findAll();

}


