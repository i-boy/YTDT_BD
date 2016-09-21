/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtPhieuKhamDtNgoaiTru;
import com.iesvn.yte.dieutri.entity.PhieuKhamDtNgoaiTru;
import java.util.List;

/**
 *
 * @author james
 */
public interface PhieuKhamDtNgoaiTruInterface {

    public void create(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru);

    public void edit(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru);

    public void remove(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru);

    public PhieuKhamDtNgoaiTru find(Object id);

    public List<PhieuKhamDtNgoaiTru> findAll();

    public List<PhieuKhamDtNgoaiTru> findByPhieuKhamDtNgoaiTru(String maPhieu);

    public PhieuKhamDtNgoaiTru findByMaThamKham(Integer mathamkham);

    public String capNhatPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru obj, String sMaPhieu, List<CtPhieuKhamDtNgoaiTru> lstCT);

    public void removeAllPhieuKhamDtNgoaiTru(PhieuKhamDtNgoaiTru phieuKhamDtNgoaiTru);

    public PhieuKhamDtNgoaiTru findBySotheBHAndMaBNAndBanKham(String sotheBH, String maBN, Integer bankham);
}
