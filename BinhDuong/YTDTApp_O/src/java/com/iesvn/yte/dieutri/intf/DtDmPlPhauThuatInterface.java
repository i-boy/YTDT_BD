/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPlPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmPlPhauThuatInterface {

public     void create(DtDmPlPhauThuat dtDmPlPhauThuat);

public     void edit(DtDmPlPhauThuat dtDmPlPhauThuat);

public     void remove(DtDmPlPhauThuat dtDmPlPhauThuat);

public     DtDmPlPhauThuat find(Object id);

public     List<DtDmPlPhauThuat> findAll();

}


