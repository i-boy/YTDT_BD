/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.delegate;
import com.iesvn.yte.locator.LookupServiceUtils;
import com.iesvn.yte.dieutri.intf.TiepDonInterface;
import com.iesvn.yte.dieutri.entity.TiepDon;
import com.iesvn.yte.dieutri.entity.TmpDataBhyt;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public class TiepDonDelegate {
private TiepDonInterface tiepdonService;
public static TiepDonDelegate getInstance() {
return new TiepDonDelegate();
}
private TiepDonInterface lookupService() {
return (TiepDonInterface)LookupServiceUtils.lookupService("TiepDonFacade");
}

public     void create(TiepDon tiepDon) {
if(tiepdonService == null) tiepdonService = lookupService();
tiepdonService.create(tiepDon);
}

public     void edit(TiepDon tiepDon) {
if(tiepdonService == null) tiepdonService = lookupService();
tiepdonService.edit(tiepDon);
}

public     void remove(TiepDon tiepDon) {
if(tiepdonService == null) tiepdonService = lookupService();
tiepdonService.remove(tiepDon);
}

public     TiepDon find(Object id) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.find(id);
}
public TiepDon findBenhNhanByTiepdonMa(String tiepdonMa){ 
	if(tiepdonService == null) tiepdonService = lookupService();
	return tiepdonService.findByTiepdonMa(tiepdonMa);
}
public     List<TiepDon> findAll() {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.findAll();
}
public TiepDon findByTiepdonMa_Kho(String tiepdonMa, Integer khoMaSo) {
    if(tiepdonService == null) tiepdonService = lookupService();
    return tiepdonService.findByTiepdonMa_Kho(tiepdonMa, khoMaSo);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.TiepDon> findTiepDonByLoaiKhoaMaAndTuNgayAndDenNgay(java.lang.String dtdmLoaiKhoaMa, java.util.Date tuNgay, java.util.Date denNgay) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.findTiepDonByLoaiKhoaMaAndTuNgayAndDenNgay(dtdmLoaiKhoaMa, tuNgay, denNgay);
}

    public java.util.List<com.iesvn.yte.dieutri.entity.TiepDon> findTiepDonByBanKhamMaAndNgay(java.lang.String banKhamMa, java.util.Date ngayThamKham) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.findTiepDonByBanKhamMaAndNgay(banKhamMa, ngayThamKham);
}

    public java.lang.String createTiepDon(com.iesvn.yte.dieutri.entity.TiepDon td) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.createTiepDon(td);
}

    public java.lang.String delHuyKham(com.iesvn.yte.dieutri.entity.TiepDon td) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.delHuyKham(td);
}

    public java.lang.String dangkyTiepDonCapCuu(com.iesvn.yte.dieutri.entity.TiepDon tiepdon, com.iesvn.yte.dieutri.entity.ThamKham thamkham) {
if(tiepdonService == null) tiepdonService = lookupService();
return tiepdonService.dangkyTiepDonCapCuu(tiepdon, thamkham);
}

    
    public List<TiepDon> findFinalByBenhNhan(String bnMa){
        if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.findFinalByBenhNhan(bnMa);
    }

      public List<TiepDon> findTiepDonByNgay(Date ngayThamKham) {
          if(tiepdonService == null) tiepdonService = lookupService();
            return tiepdonService.findTiepDonByNgay(ngayThamKham);
      }
public TiepDon findTiepDonCCByMaTiepDon(String ma, String maBanKham){
    if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.findTiepDonCCByMaTiepDon(ma, maBanKham);
}
    
public TiepDon findTiepDonCCByMaBenhNhan(String ma, String maBanKham){
    if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.findTiepDonCCByMaBenhNhan(ma, maBanKham);
}

public List<TiepDon> findTiepDonForTimKiemBN(String mabn, String ten, String matd){
    if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.findTiepDonForTimKiemBN(mabn, ten, matd);
}

 public TiepDon testBHYTTrungTrongNgay(String tiepdonSothebh, Date tiepdonNgaygio) {
     if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.testBHYTTrungTrongNgay(tiepdonSothebh, tiepdonNgaygio);
 }

 public TiepDon getTiepDonWithSoTheBHYTLast(String tiepdonSothebh) {
     if(tiepdonService == null) tiepdonService = lookupService();
        return tiepdonService.getTiepDonWithSoTheBHYTLast(tiepdonSothebh);
 }
 public void capnhatGiaClsTheoThoiGianBaoHiem(TiepDon td) {
     if(tiepdonService == null) tiepdonService = lookupService();
        tiepdonService.capnhatGiaClsTheoThoiGianBaoHiem(td);
 }
 public List<TmpDataBhyt> exportDataNgoaiTru(Date fromDate, Date toDate){
    if(tiepdonService == null) tiepdonService = lookupService();
    return tiepdonService.exportDataNgoaiTru(fromDate, toDate);
 }
 public List<TiepDon> findBySoTheBHYT(String sothebhyt){
    if(tiepdonService == null) tiepdonService = lookupService();
    return tiepdonService.findBySoTheBHYT(sothebhyt);
 } 
}


