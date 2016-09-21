/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmBanKhamInterface {

public     void create(DtDmBanKham dtDmBanKham);

public     void edit(DtDmBanKham dtDmBanKham);

public     void remove(DtDmBanKham dtDmBanKham);

public     DtDmBanKham find(Object id);

public     List<DtDmBanKham> findAll();

public List<DtDmBanKham> findByMaTiepDon(String maTiepDon);

}


