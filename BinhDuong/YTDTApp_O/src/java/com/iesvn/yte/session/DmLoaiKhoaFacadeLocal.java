/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiKhoa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiKhoaFacadeLocal {

    void create(DmLoaiKhoa dmLoaiKhoa);

    void edit(DmLoaiKhoa dmLoaiKhoa);

    void remove(DmLoaiKhoa dmLoaiKhoa);

    DmLoaiKhoa find(Object id);

    List<DmLoaiKhoa> findAll();

}


