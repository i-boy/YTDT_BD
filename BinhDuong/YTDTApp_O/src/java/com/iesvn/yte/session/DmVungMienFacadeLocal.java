/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmVungMien;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmVungMienFacadeLocal {

    void create(DmVungMien dmVungMien);

    void edit(DmVungMien dmVungMien);

    void remove(DmVungMien dmVungMien);

    DmVungMien find(Object id);

    List<DmVungMien> findAll();

}


