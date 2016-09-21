/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPhauThuat;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmPhauThuatInterface {

public     void create(DtDmPhauThuat dtDmPhauThuat);

public     void edit(DtDmPhauThuat dtDmPhauThuat);

public     void remove(DtDmPhauThuat dtDmPhauThuat);

public     DtDmPhauThuat find(Object id);

public     List<DtDmPhauThuat> findAll();

}


