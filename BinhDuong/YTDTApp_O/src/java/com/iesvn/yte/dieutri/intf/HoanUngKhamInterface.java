/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HoanUngKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HoanUngKhamInterface {

public     void create(HoanUngKham hoanUngKham);

public     void edit(HoanUngKham hoanUngKham);

public     void remove(HoanUngKham hoanUngKham);

public     HoanUngKham find(Object id);

public     List<HoanUngKham> findAll();
  public Double getTongHoanUng(String maTiepdon);

}


