/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.List;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface TonKhoInterface {

    public void create(TonKho tonKho);

    public void edit(TonKho tonKho);

    public void remove(TonKho tonKho);

    public TonKho find(Object id);

    public List<TonKho> findAll();

    public boolean insertTonKho(com.iesvn.yte.dieutri.entity.TonKho tk);

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findByTonkhoMalienket(java.lang.String malk);

    public Double findByTonkhoKhoaMalienketHienTai(String malk, Integer khoaMaso);

    public Double findByTonkhoDauMalienket(String malk, Integer khoaMaso, Date fromDateReport, Date toDateReport);

    public com.iesvn.yte.dieutri.entity.TonKho getTonKhoHienTai(java.lang.String malk, java.lang.Integer maKhoa);
    
    public List<TonKho> getTonKhoHienTai(Integer dmtMaSo, Integer khoMaso, String priority);

    public TonKho getTonKhoHienTai(Integer masoThuoc, String soLo, Double dongiaThuoc, Integer masoKhoa);

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> getTonKho(java.lang.String dmtMa, java.lang.String maKho);

    public java.lang.String getSoLuongTon(java.lang.String dmtMa, java.lang.String maKho);

    public java.lang.String getGiaBQ(java.lang.String dmtMa, java.lang.String maKho);

    public java.lang.String getGiaCuoi(java.lang.String dmtMa, java.lang.String maKho);

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findDanhSachTonKho(java.lang.String dmtMa, java.lang.String kp, java.lang.String nguon, java.lang.String khoMa);

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findTonkhoByDmThuocMa(java.lang.String dmtMa);

    public java.lang.String getSoLuongTon(java.lang.String maKho, java.lang.String maNguon, java.lang.String maKinhphi, java.lang.String maThuoc, java.lang.String quyCach, java.lang.String donGia);

    public List<TonKho> findTonKhoByDtmMaAndKhoMa(String dmtMa, String khoMa);

    public List<TonKho> findTonKhoByKhoaMasoThuocMaso(Integer thuocMaso, Integer khoMaso);

    public List<TonKho> findTonkhoByDmThuocMaso(Integer dmtMaso);

    public List<TonKho> findListTonKhoForKiemKe(String maKP, String makhoa, String maCT, String listPL, String temp, String lthuoc);
    public List<TonKho> findListTonKhoForKiemKe(Integer makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong);
    public long getTotalListTonKhoForKiemKe(String makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong);

    public TonKho findTonKhoForUpdatingOfKiemKeKho(String malk, String khoMa);

    public List<TonKho> getTonKhoForTheKho(String dmtMa, String khoMa);
    public List<TonKho> getTonKhoForTheKho(Integer dmtMaso, Integer khoMaso, Date fromDate, Date toDate);
    
    public Integer createTonKho(TonKho tonKho);

    public boolean checkTonKhoHienTai(Integer masoThuoc);
    
    public TonKho findTonkhoByMalienket(String malk) ;
}


