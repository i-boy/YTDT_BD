/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBenhVn;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmBenhVnFacadeLocal {

    void create(DmBenhVn dmBenhVn);

    void edit(DmBenhVn dmBenhVn);

    void remove(DmBenhVn dmBenhVn);

    DmBenhVn find(Object id);

    List<DmBenhVn> findAll();

}


