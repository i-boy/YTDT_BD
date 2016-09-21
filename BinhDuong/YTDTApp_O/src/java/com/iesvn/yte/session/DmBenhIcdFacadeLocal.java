/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhIcd;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmBenhIcdFacadeLocal {

    void create(DmBenhIcd dmBenhIcd);

    void edit(DmBenhIcd dmBenhIcd);

    void remove(DmBenhIcd dmBenhIcd);

    DmBenhIcd find(Object id);

    List<DmBenhIcd> findAll();

}


