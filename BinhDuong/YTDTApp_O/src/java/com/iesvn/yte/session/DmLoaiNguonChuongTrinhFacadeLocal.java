/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiNguonChuongTrinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiNguonChuongTrinhFacadeLocal {

    void create(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh);

    void edit(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh);

    void remove(DmLoaiNguonChuongTrinh dmLoaiNguonChuongTrinh);

    DmLoaiNguonChuongTrinh find(Object id);

    List<DmLoaiNguonChuongTrinh> findAll();

}


