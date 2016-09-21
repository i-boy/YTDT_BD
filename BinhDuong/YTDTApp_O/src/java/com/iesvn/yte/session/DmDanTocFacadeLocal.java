/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDanToc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDanTocFacadeLocal {

    void create(DmDanToc dmDanToc);

    void edit(DmDanToc dmDanToc);

    void remove(DmDanToc dmDanToc);

    DmDanToc find(Object id);

    List<DmDanToc> findAll();

}


