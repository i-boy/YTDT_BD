/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtXuatBh;
import com.iesvn.yte.dieutri.entity.PhieuXuatBh;
import com.iesvn.yte.dieutri.entity.TonKho;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;

import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface PhieuXuatBhInterface {

    public void create(PhieuXuatBh phieuXuatBh);

    public void edit(PhieuXuatBh phieuXuatBh);

    public void remove(PhieuXuatBh phieuXuatBh);

    public PhieuXuatBh find(Object id);

    public List<PhieuXuatBh> findAll();

    public String create(PhieuXuatBh phieuxuatBh, List<CtXuatBh> listCtXuatBh, List<TonKho> listTk);

    public List<PhieuXuatBh> findByTiepDonMa(String tiepDonMa);
    public List<PhieuXuatBh> findByTiepDonMa_Kho(String tiepDonMa, Integer khoMaso);

    public String createByThuocPhongKham(Integer KhoaMaSo, PhieuXuatBh phieuxuatBh, List<ThuocPhongKham> listTPK, List<CtXuatBh> listCtXuatBH, String priority);
}


