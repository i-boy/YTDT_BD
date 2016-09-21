/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmChuongBenh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmChuongBenhFacadeLocal {

    void create(DmChuongBenh dmChuongBenh);

    void edit(DmChuongBenh dmChuongBenh);

    void remove(DmChuongBenh dmChuongBenh);

    DmChuongBenh find(Object id);

    List<DmChuongBenh> findAll();

}


