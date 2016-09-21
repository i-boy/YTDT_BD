/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ClsKham;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ClsKhamInterface {

    public void create(ClsKham clsKham);

    public void edit(ClsKham clsKham);

    public void remove(ClsKham clsKham);

    public ClsKham find(Object id);

    public List<ClsKham> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByTiepdonma(java.lang.String tiepdonMa);

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByBanKhamVaMaTiepDon(java.lang.String banKham, java.lang.String maTiepDon);

    public String createClsKham(List<ClsKham> listCLS, String maTiepDon, String maBanKham);

    public String createClsKham(List<ClsKham> listCLS, String maTiepDon);

    public String createClsKhamHoanTra(List<ClsKham> listCLS, String maTiepDon, String maBanKham);

    public String createClsKhamHoanTra(List<ClsKham> listCLS, String maTiepDon);

    public void removeItem(java.lang.String listclsMa, java.lang.String tiepdonMa, String maBanKham);

    public java.lang.String findTiepdonMa(java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> listCLS);

    public java.util.List<com.iesvn.yte.dieutri.entity.ClsKham> findByTiepdonMaBhyt(java.lang.String tiepdonMa);

    public List<ClsKham> findByMaPhieu(String maPhieu);

    public String createClsKhamForCLSPhauThuat(List<ClsKham> listCLS, String maTiepDon, String maBanKham);

    public List<ClsKham> findByMaPhieuHoan(String maPhieu);

    public Double getSoTienTuPhieu(String maTiepDon, String maPhieu);

    public List<ClsKham> findByTiepdonMaVaLoaiClsKham(String tiepdonMa);
    
    public List<ClsKham> findByTiepdonmaAndKhoa(String tiepdonMa, String khoaMaso);
    
    public String createClsKham_v2(List<ClsKham> listCLS, String maTiepDon, String maPhieuTT) ;   
}


