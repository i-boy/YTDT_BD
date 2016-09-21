/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmBacSiBanKham;
import java.util.List;

/**
 *
 * @author root
 */
public interface DtDmBacSiBanKhamInterface {

    void create(DtDmBacSiBanKham dtDmBacSiBanKham);

    void edit(DtDmBacSiBanKham dtDmBacSiBanKham);

    void remove(DtDmBacSiBanKham dtDmBacSiBanKham);

    DtDmBacSiBanKham find(Object id);

    List<DtDmBacSiBanKham> findAll();
}


