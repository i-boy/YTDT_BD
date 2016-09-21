/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhomBhyt;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface DtDmNhomBhytInterface {
     void create(DtDmNhomBhyt dtDmNhomBhyt);

    void edit(DtDmNhomBhyt dtDmNhomBhyt);

    void remove(DtDmNhomBhyt dtDmNhomBhyt);

    DtDmNhomBhyt find(Object id);

    List<DtDmNhomBhyt> findAll();
}
