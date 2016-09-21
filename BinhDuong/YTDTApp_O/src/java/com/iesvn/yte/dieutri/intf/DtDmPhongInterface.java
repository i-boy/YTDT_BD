/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPhong;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmPhongInterface {

    void create(DtDmPhong dtDmPhong);

    void edit(DtDmPhong dtDmPhong);

    void remove(DtDmPhong dtDmPhong);

    DtDmPhong find(Object id);

    List<DtDmPhong> findAll();
}
