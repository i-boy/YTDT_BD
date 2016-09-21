/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKhoaBc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmKhoaBcFacadeLocal {

    void create(DmKhoaBc dmKhoaBc);

    void edit(DmKhoaBc dmKhoaBc);

    void remove(DmKhoaBc dmKhoaBc);

    DmKhoaBc find(Object id);

    List<DmKhoaBc> findAll();

}


