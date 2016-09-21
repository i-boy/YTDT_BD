/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmCum;
import java.util.List;

/**
 *
 * @author root
 */
public interface DtDmCumInterface {
    void create(DtDmCum dtDmCum);

    void edit(DtDmCum dtDmCum);

    void remove(DtDmCum dtDmCum);

    DtDmCum find(Object id);

    List<DtDmCum> findAll();
}
