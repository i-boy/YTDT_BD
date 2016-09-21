/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HoanThuKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HoanThuKhamInterface {

public     void create(HoanThuKham hoanThuKham);

public     void edit(HoanThuKham hoanThuKham);

public     void remove(HoanThuKham hoanThuKham);

public     HoanThuKham find(Object id);

public     List<HoanThuKham> findAll();

}


