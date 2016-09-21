/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.HsbaChuyenMonInterface;

import com.iesvn.yte.dieutri.entity.HsbaChuyenMon;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class HsbaChuyenMonDelegate {
private HsbaChuyenMonInterface hsbachuyenmonService;
public static HsbaChuyenMonDelegate getInstance() {
return new HsbaChuyenMonDelegate();
}
private HsbaChuyenMonInterface lookupService() {
return (HsbaChuyenMonInterface)LookupServiceUtils.lookupService("HsbaChuyenMonFacade");
}

public     void create(HsbaChuyenMon hsbaChuyenMon) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
hsbachuyenmonService.create(hsbaChuyenMon);
}

public     void edit(HsbaChuyenMon hsbaChuyenMon) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
hsbachuyenmonService.edit(hsbaChuyenMon);
}

public     void remove(HsbaChuyenMon hsbaChuyenMon) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
hsbachuyenmonService.remove(hsbaChuyenMon);
}

public     HsbaChuyenMon find(Object id) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.find(id);
}

public     List<HsbaChuyenMon> findAll() {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findAll();
}

    public com.iesvn.yte.dieutri.entity.HsbaChuyenMon findBySoVaoVien_MaKhoa(java.lang.String soVaoVien, java.lang.String maKhoa) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findBySoVaoVien_MaKhoa(soVaoVien, maKhoa);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findByMaKhoa_ChuaCapNhat(java.lang.String maKhoa) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findByMaKhoa_ChuaCapNhat(maKhoa);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findBySoVaoVien(java.lang.String soVaoVien) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findBySoVaoVien(soVaoVien);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.HsbaChuyenMon> findByMaLoaiKhoaTuNgayAndDenNgay(java.lang.String maLoaiKhoa, java.util.Date tuNgay, java.util.Date denNgay) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findByMaLoaiKhoaTuNgayAndDenNgay(maLoaiKhoa, tuNgay, denNgay);
}

    public int findTonDauKy(java.lang.String maLoaiKhoa, java.util.Date tuNgay) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findTonDauKy(maLoaiKhoa, tuNgay);
}

    public java.lang.String capNhatHoSoBenhAn(com.iesvn.yte.dieutri.entity.Hsba hsba, com.iesvn.yte.dieutri.entity.HsbaChuyenMon hsbaCM, com.iesvn.yte.dieutri.entity.HsbaChuyenVien hsbaCV, com.iesvn.yte.dieutri.entity.HsbaNop hsbaNop, com.iesvn.yte.dieutri.entity.BenhNhan benhNhan) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.capNhatHoSoBenhAn(hsba, hsbaCM, hsbaCV, hsbaNop, benhNhan);
}

    public com.iesvn.yte.dieutri.entity.HsbaChuyenMon findByTiepDonMa(java.lang.String maTiepDon) {
if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findByTiepDonMa(maTiepDon);
}
 public HsbaChuyenMon findBySoVaoVien_LastHSBACM(String soVaoVien) {
     if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.findBySoVaoVien_LastHSBACM(soVaoVien);
 }
 
  public String xoaHSBAChuyenMon(String soVaoVien){
       if(hsbachuyenmonService == null) hsbachuyenmonService = lookupService();
return hsbachuyenmonService.xoaHSBAChuyenMon(soVaoVien);
      
  }
          
}


