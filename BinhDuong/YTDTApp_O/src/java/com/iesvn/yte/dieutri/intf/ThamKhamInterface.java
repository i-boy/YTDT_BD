/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.BenhNhan;
import com.iesvn.yte.dieutri.entity.HsThtoank;
import com.iesvn.yte.dieutri.entity.ThamKham;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.ClsKham;
import com.iesvn.yte.dieutri.entity.Hsba;
import com.iesvn.yte.dieutri.entity.HsbaKhoa;
import com.iesvn.yte.dieutri.entity.ThuocPhongKham;
import com.iesvn.yte.entity.DmKhoa;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ThamKhamInterface {

    public void create(ThamKham thamKham);

    public void edit(ThamKham thamKham);

    public void remove(ThamKham thamKham);

    public ThamKham find(Object id);

    public List<ThamKham> findAll();

    public List<ThamKham> findThamKhamByBanKhamMaAndNgay(String banKhamMa, Date ngayThamKham);

    public ThamKham findByBanKhamVaMaTiepDon(String banKham, String maTiepDon);

    public ThamKham findByMaTiepDon(String maTiepDon);

    public ThamKham findThamKhamByMaTiepDonFirst(String maTiepDon);

    public ThamKham findByBanKhamWithLoaiKhoaVaMaTiepDon(String dtdmloaikhoaMa, String maTiepDon);

    public String thamKhamVaXuTri(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String cvntOption);

    public String vaoNoiTru_ChuyenSoLieu(HsThtoank hsttk, ThamKham thamkham, TiepDon tiepdon, BenhNhan benhnhan, Boolean chuyenVaoNT, String huongXuLy, String cvntOption);

    public List<ThamKham> findThamKhamByNgay(Date ngayThamKham);

    public String chuyenSoLieuVaoNoiTru(Hsba hsba, HsbaKhoa hsbaKhoa, DmKhoa khoa, String matiepdon);

    public String getMaTiepDonByGoiBN(String maBanKham);

    public String xoaSoThuThuBNKham(String maTiepDon);

    public List<ThamKham> findAllByMaTiepDon(String maTiepDon);

    public ThamKham getLastThamKhamWithSoTheBHYTAndBanKham(String tiepdonSothebh, Integer dtdmbankhamMaso);

    public String vaoNoiTru_Temp(ThamKham thamkham, TiepDon tiepdon, String hsbaType, DmKhoa khoaNoitru);

    public List<ClsKham> getListClsBanKhamTruoc(String tiepdonMa, Integer thamkhamMa);

    public List<ThuocPhongKham> getListThuocBanKhamTruoc(String tiepdonMa, Integer thamkhamMa);
}


