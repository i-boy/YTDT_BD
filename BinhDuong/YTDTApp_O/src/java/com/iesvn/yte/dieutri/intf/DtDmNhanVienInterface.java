/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmBanKham;
import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.entity.DmKhoa;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DtDmNhanVienInterface {

    public void create(DtDmNhanVien dtDmNhanVien);

    public void edit(DtDmNhanVien dtDmNhanVien);

    public void remove(DtDmNhanVien dtDmNhanVien);

    public DtDmNhanVien find(Object id);

    public List<DtDmNhanVien> findAll();
    
    public List<DtDmBanKham> getListBanKham(Integer nvMaso);
    
    public List<DmKhoa> getListKhoa(Integer nvMaso);
    
    public DtDmNhanVien findByNd(String tenNd);

    public DtDmNhanVien findByMaNhanVien(String dtdmnhanvienMa);

    public DtDmNhanVien findByTenNhanVien(String dtdmnhanvienTen);
    
    public List<DtDmNhanVien> findAllWithOrderBy();
}


