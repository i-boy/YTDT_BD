/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.entity.DmPhanLoaiThuoc;
import java.util.List;
/**
 *
 * @author ThoVNA
 */
public interface DmPhanLoaiThuocInterface {
    public void create(DmPhanLoaiThuoc dmPhanLoaiThuoc);
    public void edit(DmPhanLoaiThuoc dmPhanLoaiThuoc);
    public void remove(DmPhanLoaiThuoc dmPhanLoaiThuoc);
    public DmPhanLoaiThuoc find(Object id);
    public List<DmPhanLoaiThuoc> findAll();    
    public List<DmPhanLoaiThuoc> findByDtdmloaiMa(String maLoai);
    public List<DmPhanLoaiThuoc> findByMaAndTenAndDmloaiMa(String ma, String ten, Integer dmLoaithuocMaso);
}
