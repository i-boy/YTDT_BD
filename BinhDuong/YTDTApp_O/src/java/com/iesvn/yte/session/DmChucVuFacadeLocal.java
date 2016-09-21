/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmChucVu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmChucVuFacadeLocal {

    void create(DmChucVu dmChucVu);

    void edit(DmChucVu dmChucVu);

    void remove(DmChucVu dmChucVu);

    DmChucVu find(Object id);

    List<DmChucVu> findAll();

}


