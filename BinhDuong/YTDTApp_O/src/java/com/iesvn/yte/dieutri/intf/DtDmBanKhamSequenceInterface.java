/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmBanKhamSequence;
import java.util.List;

/**
 *
 * @author root
 */
public interface DtDmBanKhamSequenceInterface {

    void create(DtDmBanKhamSequence dtDmBanKhamSequence);

    void edit(DtDmBanKhamSequence dtDmBanKhamSequence);

    void remove(DtDmBanKhamSequence dtDmBanKhamSequence);

    DtDmBanKhamSequence find(Object id);

    List<DtDmBanKhamSequence> findAll();

    public int reset();
}
