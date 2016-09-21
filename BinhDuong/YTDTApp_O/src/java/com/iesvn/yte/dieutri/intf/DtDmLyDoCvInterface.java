/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmLyDoCv;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmLyDoCvInterface {

public     void create(DtDmLyDoCv dtDmLyDoCv);

public     void edit(DtDmLyDoCv dtDmLyDoCv);

public     void remove(DtDmLyDoCv dtDmLyDoCv);

public     DtDmLyDoCv find(Object id);

public     List<DtDmLyDoCv> findAll();

}


