/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmMqlBhyt;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmMqlBhytInterface {

    public void create(DtDmMqlBhyt dtDmMqlBhyt);

    public void edit(DtDmMqlBhyt dtDmMqlBhyt);

    public void remove(DtDmMqlBhyt dtDmMqlBhyt);

    public DtDmMqlBhyt find(Object id);

    public List<DtDmMqlBhyt> findAll();

    public DtDmMqlBhyt findByMa(String maQL);
}


