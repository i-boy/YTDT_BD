/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmSanPhamDinhDuong;
import java.util.List;

/**
 *
 * @author james
 */
public interface DtDmSanPhamDinhDuongInterface {

    public void create(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong);

    public void edit(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong);

    public void remove(DtDmSanPhamDinhDuong dtDmSanPhamDinhDuong);

    public DtDmSanPhamDinhDuong find(Object id);

    public List<DtDmSanPhamDinhDuong> findAll();
}
