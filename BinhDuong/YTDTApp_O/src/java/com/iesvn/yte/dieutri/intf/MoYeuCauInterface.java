/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.MoYeuCau;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface MoYeuCauInterface {

public     void create(MoYeuCau moYeuCau);

public     void edit(MoYeuCau moYeuCau);

public     void remove(MoYeuCau moYeuCau);

public     MoYeuCau find(Object id);

public     List<MoYeuCau> findAll();

}


