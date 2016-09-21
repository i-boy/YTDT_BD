/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ClsKetQua;
import java.util.List;

/**
 *
 * @author Thanh
 */
public interface ClsKetQuaInterface {

    public void create(ClsKetQua clsKetQua);

    public void edit(ClsKetQua clsKetQua);

    public void remove(ClsKetQua clsKetQua);

    public ClsKetQua find(Object id);

    public List<ClsKetQua> findAll();
    
    
}
