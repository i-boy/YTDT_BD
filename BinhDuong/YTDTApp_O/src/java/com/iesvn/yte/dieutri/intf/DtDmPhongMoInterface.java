/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmPhongMo;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmPhongMoInterface {

public     void create(DtDmPhongMo dtDmPhongMo);

public     void edit(DtDmPhongMo dtDmPhongMo);

public     void remove(DtDmPhongMo dtDmPhongMo);

public     DtDmPhongMo find(Object id);

public     List<DtDmPhongMo> findAll();

}


