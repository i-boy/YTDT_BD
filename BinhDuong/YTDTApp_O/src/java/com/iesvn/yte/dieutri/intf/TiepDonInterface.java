/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TiepDon;
import java.util.Date;
import java.util.List;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface TiepDonInterface {

    public void create(TiepDon tiepDon);

    public void edit(TiepDon tiepDon);

    public void remove(TiepDon tiepDon);

    public TiepDon find(Object id);

    public TiepDon findByTiepdonMa(String tiepdonMa);

    public TiepDon findByTiepdonMa_Kho(String tiepdonMa, Integer khoMaSo);

    public List<TiepDon> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.TiepDon> findTiepDonByLoaiKhoaMaAndTuNgayAndDenNgay(java.lang.String dtdmLoaiKhoaMa, java.util.Date tuNgay, java.util.Date denNgay);

    public java.util.List<com.iesvn.yte.dieutri.entity.TiepDon> findTiepDonByBanKhamMaAndNgay(java.lang.String banKhamMa, java.util.Date ngayThamKham);

    public java.lang.String createTiepDon(com.iesvn.yte.dieutri.entity.TiepDon td);

    public java.lang.String delHuyKham(com.iesvn.yte.dieutri.entity.TiepDon td);

    public java.lang.String dangkyTiepDonCapCuu(com.iesvn.yte.dieutri.entity.TiepDon tiepdon, com.iesvn.yte.dieutri.entity.ThamKham thamkham);

    public List<TiepDon> findTiepDonByNgay(java.util.Date ngayThamKham);

    public List<TiepDon> findFinalByBenhNhan(String bnMa);

    public TiepDon findTiepDonCCByMaTiepDon(String ma, String maBanKham);

    public TiepDon findTiepDonCCByMaBenhNhan(String ma, String maBanKham);

    public List<TiepDon> findTiepDonForTimKiemBN(String mabn, String ten, String matd);

    public TiepDon testBHYTTrungTrongNgay(String tiepdonSothebh, Date tiepdonNgaygio);

    public TiepDon getTiepDonWithSoTheBHYTLast(String tiepdonSothebh);

    public void capnhatGiaClsTheoThoiGianBaoHiem(TiepDon td);

    public List<TiepDon> findBySoTheBHYT(String sothebhyt);

    public List<TmpDataBhyt> exportDataNgoaiTru(Date fromDate, Date toDate);
}


