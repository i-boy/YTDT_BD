/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuGiaoBan;
import com.iesvn.yte.dieutri.entity.PhieuGiaoBanThanhPhanThamDu;
import java.util.List;

/**
 *
 * @author james
 */
public interface PhieuGiaoBanThanhPhanThamDuInterface {

    public void create(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu);

    public void edit(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu);

    public void remove(PhieuGiaoBanThanhPhanThamDu phieuGiaoBanThanhPhanThamDu);

    public List<PhieuGiaoBanThanhPhanThamDu> findByPhieuGiaoBan(String maPhieu) ;

    public List<PhieuGiaoBanThanhPhanThamDu> findByThamDu(String maPhieu);

    public List<PhieuGiaoBanThanhPhanThamDu> findByVangMat(String maPhieu);

    public PhieuGiaoBanThanhPhanThamDu find(Object id);

    public List<PhieuGiaoBanThanhPhanThamDu> findAll();

    public void capNhatPhieuGiaoBanTPTD(List<PhieuGiaoBanThanhPhanThamDu> listTPTD, PhieuGiaoBan phieuGiaoBan);
}
