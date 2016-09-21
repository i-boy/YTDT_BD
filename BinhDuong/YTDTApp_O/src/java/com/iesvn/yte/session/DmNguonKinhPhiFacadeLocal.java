/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNguonKinhPhi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNguonKinhPhiFacadeLocal {

    void create(DmNguonKinhPhi dmNguonKinhPhi);

    void edit(DmNguonKinhPhi dmNguonKinhPhi);

    void remove(DmNguonKinhPhi dmNguonKinhPhi);

    DmNguonKinhPhi find(Object id);

    List<DmNguonKinhPhi> findAll();

}


