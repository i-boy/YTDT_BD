/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNhomBaoCaoThuoc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNhomBaoCaoThuocFacadeLocal {

    void create(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc);

    void edit(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc);

    void remove(DmNhomBaoCaoThuoc dmNhomBaoCaoThuoc);

    DmNhomBaoCaoThuoc find(Object id);

    List<DmNhomBaoCaoThuoc> findAll();

}


