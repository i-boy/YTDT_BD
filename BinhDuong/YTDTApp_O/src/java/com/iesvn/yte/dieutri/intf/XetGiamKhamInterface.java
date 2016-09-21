/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.XetGiamKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface XetGiamKhamInterface {

public     void create(XetGiamKham xetGiamKham);

public     void edit(XetGiamKham xetGiamKham);

public     void remove(XetGiamKham xetGiamKham);

public     XetGiamKham find(Object id);

public     List<XetGiamKham> findAll();

}


