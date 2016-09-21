/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtTraKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtTraKhoInterface {

public     void create(CtTraKho ctTraKho);

public     void edit(CtTraKho ctTraKho);

public     void remove(CtTraKho ctTraKho);

public     CtTraKho find(Object id);

public     List<CtTraKho> findAll();

public List<CtTraKho> findByphieutrakhoMa(String phieutraMa);

}


