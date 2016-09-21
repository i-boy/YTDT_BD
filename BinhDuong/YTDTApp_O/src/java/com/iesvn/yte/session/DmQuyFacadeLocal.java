/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuy;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmQuyFacadeLocal {

    void create(DmQuy dmQuy);

    void edit(DmQuy dmQuy);

    void remove(DmQuy dmQuy);

    DmQuy find(Object id);

    List<DmQuy> findAll();

}


