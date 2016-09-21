/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmBaoQuanThuoc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmBaoQuanThuocFacadeLocal {

    void create(DmBaoQuanThuoc dmBaoQuanThuoc);

    void edit(DmBaoQuanThuoc dmBaoQuanThuoc);

    void remove(DmBaoQuanThuoc dmBaoQuanThuoc);

    DmBaoQuanThuoc find(Object id);

    List<DmBaoQuanThuoc> findAll();

}


