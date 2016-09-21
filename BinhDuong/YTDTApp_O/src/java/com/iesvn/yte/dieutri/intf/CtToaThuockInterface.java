/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtToaThuock;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtToaThuockInterface {

public     void create(CtToaThuock ctToaThuock);

public     void edit(CtToaThuock ctToaThuock);

public     void remove(CtToaThuock ctToaThuock);

public     CtToaThuock find(Object id);

public     List<CtToaThuock> findAll();

}


