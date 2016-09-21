/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmTramYTeBhyt;
import java.util.List;

/**
 *
 * @author thanh 
 */
public interface DtDmTramYTeBhytInterface {
  void create(DtDmTramYTeBhyt dtDmTramYTeBhyt);

    void edit(DtDmTramYTeBhyt dtDmTramYTeBhyt);

    void remove(DtDmTramYTeBhyt dtDmTramYTeBhyt);

    DtDmTramYTeBhyt find(Object id);

    List<DtDmTramYTeBhyt> findAll();
}
