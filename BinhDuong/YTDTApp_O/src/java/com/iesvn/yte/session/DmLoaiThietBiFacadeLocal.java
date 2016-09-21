/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiThietBi;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiThietBiFacadeLocal {

    void create(DmLoaiThietBi dmLoaiThietBi);

    void edit(DmLoaiThietBi dmLoaiThietBi);

    void remove(DmLoaiThietBi dmLoaiThietBi);

    DmLoaiThietBi find(Object id);

    List<DmLoaiThietBi> findAll();

}


