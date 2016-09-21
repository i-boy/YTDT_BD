/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.PhieuXuatBhXuatVien;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;

import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuXuatBhXuatVienInterface {

    public void create(PhieuXuatBhXuatVien phieuXuatBh);

    public void edit(PhieuXuatBhXuatVien phieuXuatBh);

    public void remove(PhieuXuatBhXuatVien phieuXuatBh);

    public PhieuXuatBhXuatVien find(Object id);

    public List<PhieuXuatBhXuatVien> findAll();

    public String create(PhieuXuatBhXuatVien phieuxuatBh, List<CtXuatBhXuatVien> listCtXuatBh, List<TonKho> listTk);

    public List<PhieuXuatBhXuatVien> findBySoVaoVien(String soVaovien);
    public List<PhieuXuatBhXuatVien> findBySovaovien_Kho(String soVaovien, Integer khoMaso);

    public String createByThuocNoiTru(int KhoaMaSo, PhieuXuatBhXuatVien phieuxuatBh, List<ThuocNoiTru> listTNT, List<CtXuatBhXuatVien> listCtXuatBH);
}


