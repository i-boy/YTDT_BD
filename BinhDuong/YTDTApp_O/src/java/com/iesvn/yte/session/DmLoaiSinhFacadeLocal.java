/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiSinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiSinhFacadeLocal {

    void create(DmLoaiSinh dmLoaiSinh);

    void edit(DmLoaiSinh dmLoaiSinh);

    void remove(DmLoaiSinh dmLoaiSinh);

    DmLoaiSinh find(Object id);

    List<DmLoaiSinh> findAll();

}


