/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiHoSo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiHoSoFacadeLocal {

    void create(DmLoaiHoSo dmLoaiHoSo);

    void edit(DmLoaiHoSo dmLoaiHoSo);

    void remove(DmLoaiHoSo dmLoaiHoSo);

    DmLoaiHoSo find(Object id);

    List<DmLoaiHoSo> findAll();

}


