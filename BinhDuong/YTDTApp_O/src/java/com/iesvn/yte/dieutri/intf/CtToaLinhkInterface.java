/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtToaLinhk;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtToaLinhkInterface {

public     void create(CtToaLinhk ctToaLinhk);

public     void edit(CtToaLinhk ctToaLinhk);

public     void remove(CtToaLinhk ctToaLinhk);

public     CtToaLinhk find(Object id);

public     List<CtToaLinhk> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.CtToaLinhk> findByTiepDonMa(java.lang.String tiepdonMa);

}


