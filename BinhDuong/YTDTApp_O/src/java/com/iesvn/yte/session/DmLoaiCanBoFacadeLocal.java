/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiCanBo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiCanBoFacadeLocal {

    void create(DmLoaiCanBo dmLoaiCanBo);

    void edit(DmLoaiCanBo dmLoaiCanBo);

    void remove(DmLoaiCanBo dmLoaiCanBo);

    DmLoaiCanBo find(Object id);

    List<DmLoaiCanBo> findAll();

}


