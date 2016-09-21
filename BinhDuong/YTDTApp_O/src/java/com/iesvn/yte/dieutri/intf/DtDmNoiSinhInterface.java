/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNoiSinh;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmNoiSinhInterface {

public     void create(DtDmNoiSinh dtDmNoiSinh);

public     void edit(DtDmNoiSinh dtDmNoiSinh);

public     void remove(DtDmNoiSinh dtDmNoiSinh);

public     DtDmNoiSinh find(Object id);

public     List<DtDmNoiSinh> findAll();

}


