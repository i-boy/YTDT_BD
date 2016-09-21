/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DeNghiCc;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DeNghiCcInterface {

public     void create(DeNghiCc deNghiCc);

public     void edit(DeNghiCc deNghiCc);

public     void remove(DeNghiCc deNghiCc);

public     DeNghiCc find(Object id);

public     List<DeNghiCc> findAll();

}


