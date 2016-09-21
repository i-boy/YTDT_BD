/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TonKhoInterface;

import com.iesvn.yte.dieutri.entity.TonKho;
import java.util.List;
import java.util.Date;
/**
 *
 * @author LENOVO 3000 Y410
 */
public class TonKhoDelegate {
private TonKhoInterface tonkhoService;
public static TonKhoDelegate getInstance() {
return new TonKhoDelegate();
}
private TonKhoInterface lookupService() {
return (TonKhoInterface)LookupServiceUtils.lookupService("TonKhoFacade");
}

public     void create(TonKho tonKho) {
if(tonkhoService == null) tonkhoService = lookupService();
tonkhoService.create(tonKho);
}

public     void edit(TonKho tonKho) {
if(tonkhoService == null) tonkhoService = lookupService();
tonkhoService.edit(tonKho);
}

public     void remove(TonKho tonKho) {
if(tonkhoService == null) tonkhoService = lookupService();
tonkhoService.remove(tonKho);
}

public     TonKho find(Object id) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.find(id);
}

public     List<TonKho> findAll() {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.findAll();
}

    public boolean insertTonKho(com.iesvn.yte.dieutri.entity.TonKho tk) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.insertTonKho(tk);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findByTonkhoMalienket(java.lang.String malk) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.findByTonkhoMalienket(malk);
}
    public Double findByTonkhoDauMalienket(String malk, Integer khoaMaso, Date fromDateReport, Date toDateReport)
    {
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.findByTonkhoDauMalienket(malk, khoaMaso, fromDateReport, toDateReport);
    }

    public Double findByTonkhoKhoaMalienketHienTai(String malk, Integer khoaMaso){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.findByTonkhoKhoaMalienketHienTai(malk, khoaMaso);
    }

    public com.iesvn.yte.dieutri.entity.TonKho getTonKhoHienTai(java.lang.String malk, java.lang.Integer maKhoa) {
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.getTonKhoHienTai(malk, maKhoa);
    }

    public List<TonKho> getTonKhoHienTai(Integer dmtMaSo, Integer khoMaso, String priority){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.getTonKhoHienTai(dmtMaSo, khoMaso, priority);
    }

    public TonKho getTonKhoHienTai(Integer masoThuoc, String soLo, Double dongiaThuoc, Integer masoKhoa){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.getTonKhoHienTai(masoThuoc, soLo, dongiaThuoc, masoKhoa);
    }
    
    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> getTonKho(java.lang.String dmtMa, java.lang.String maKho) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.getTonKho(dmtMa, maKho);
}

    public java.lang.String getSoLuongTon(java.lang.String dmtMa, java.lang.String maKho) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.getSoLuongTon(dmtMa, maKho);
}

    public java.lang.String getGiaBQ(java.lang.String dmtMa, java.lang.String maKho) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.getGiaBQ(dmtMa, maKho);
}

    public java.lang.String getGiaCuoi(java.lang.String dmtMa, java.lang.String maKho) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.getGiaCuoi(dmtMa, maKho);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findDanhSachTonKho(java.lang.String dmtMa, java.lang.String kp, java.lang.String nguon, java.lang.String khoMa) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.findDanhSachTonKho(dmtMa, kp, nguon, khoMa);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.TonKho> findTonkhoByDmThuocMa(java.lang.String dmtMa) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.findTonkhoByDmThuocMa(dmtMa);
}

    public java.lang.String getSoLuongTon(java.lang.String maKho, java.lang.String maNguon, java.lang.String maKinhphi, java.lang.String maThuoc, java.lang.String quyCach, java.lang.String donGia) {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.getSoLuongTon(maKho, maNguon, maKinhphi, maThuoc, quyCach, donGia);
}
    public List<TonKho> findTonKhoByDtmMaAndKhoMa(String dmtMa, String khoMa)  {
if(tonkhoService == null) tonkhoService = lookupService();
return tonkhoService.findTonKhoByDtmMaAndKhoMa(dmtMa, khoMa);
}

    public List<TonKho> findTonKhoByKhoaMasoThuocMaso(Integer thuocMaso, Integer khoMaso){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.findTonKhoByKhoaMasoThuocMaso(thuocMaso, khoMaso);
    }
    
     public List<TonKho> findTonkhoByDmThuocMaso(Integer dmtMaso){
         if(tonkhoService == null) tonkhoService = lookupService();
         return tonkhoService.findTonkhoByDmThuocMaso(dmtMaso);
     }
     
     public List<TonKho> findListTonKhoForKiemKe(String maKP,String makhoa,String maCT,String listPL,String temp,String lthuoc){
         if(tonkhoService == null) tonkhoService = lookupService();
            return tonkhoService.findListTonKhoForKiemKe(maKP,makhoa,maCT,listPL,temp,lthuoc);
     }

     public List<TonKho> findListTonKhoForKiemKe(Integer makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.findListTonKhoForKiemKe(makhoa,listPL,lthuoc, kecaTonBangKhong);
     }

     public long getTotalListTonKhoForKiemKe(String makhoa, String listPL, String lthuoc, boolean kecaTonBangKhong){
        if(tonkhoService == null) tonkhoService = lookupService();
        return tonkhoService.getTotalListTonKhoForKiemKe(makhoa,listPL,lthuoc, kecaTonBangKhong);
     }
     
     public TonKho findTonKhoForUpdatingOfKiemKeKho(String malk, String khoMa){
         if(tonkhoService == null) tonkhoService = lookupService();
            return tonkhoService.findTonKhoForUpdatingOfKiemKeKho(malk,  khoMa);
     }
      public  List<TonKho> getTonKhoForTheKho(String dmtMa, String khoMa){
         if(tonkhoService == null) tonkhoService = lookupService();
            return tonkhoService.getTonKhoForTheKho(dmtMa,khoMa);
     }

      public  List<TonKho> getTonKhoForTheKho(Integer dmtMaso, Integer khoMaso, Date fromDate, Date toDate){
         if(tonkhoService == null) tonkhoService = lookupService();
            return tonkhoService.getTonKhoForTheKho(dmtMaso,khoMaso, fromDate, toDate);
      }
      public Integer createTonKho(TonKho tonKho){
          if(tonkhoService == null) tonkhoService = lookupService();
            return tonkhoService.createTonKho(tonKho);
      }

      public boolean checkTonKhoHienTai(Integer masoThuoc){
          if(tonkhoService == null) tonkhoService = lookupService();
          return tonkhoService.checkTonKhoHienTai(masoThuoc);
      }
      public TonKho findTonkhoByMalienket(String malk) {
          if(tonkhoService == null) tonkhoService = lookupService();
          return tonkhoService.findTonkhoByMalienket(malk);
      }
}


