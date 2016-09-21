/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmLoaiBenhTruyenNhiem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmLoaiBenhTruyenNhiemFacadeLocal {

    void create(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem);

    void edit(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem);

    void remove(DmLoaiBenhTruyenNhiem dmLoaiBenhTruyenNhiem);

    DmLoaiBenhTruyenNhiem find(Object id);

    List<DmLoaiBenhTruyenNhiem> findAll();

}


