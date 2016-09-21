/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhaCungCap;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNhaCungCapFacadeLocal {

    void create(DmNhaCungCap dmNhaCungCap);

    void edit(DmNhaCungCap dmNhaCungCap);

    void remove(DmNhaCungCap dmNhaCungCap);

    DmNhaCungCap find(Object id);

    List<DmNhaCungCap> findAll();

}


