/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ToaThuocKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ToaThuocKhamInterface {

public     void create(ToaThuocKham toaThuocKham);

public     void edit(ToaThuocKham toaThuocKham);

public     void remove(ToaThuocKham toaThuocKham);

public     ToaThuocKham find(Object id);

public     List<ToaThuocKham> findAll();

}


