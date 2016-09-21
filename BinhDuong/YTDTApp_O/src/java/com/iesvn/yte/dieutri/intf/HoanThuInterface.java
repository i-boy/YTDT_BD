/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HoanThu;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HoanThuInterface {

public     void create(HoanThu hoanThu);

public     void edit(HoanThu hoanThu);

public     void remove(HoanThu hoanThu);

public     HoanThu find(Object id);

public     List<HoanThu> findAll();

}


