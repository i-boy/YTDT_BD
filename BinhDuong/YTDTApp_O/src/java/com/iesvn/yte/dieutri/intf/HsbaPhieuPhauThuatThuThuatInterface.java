/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DtDmNhanVien;
import com.iesvn.yte.dieutri.entity.HsbaPhieuPhauThuatThuThuat;
import java.util.List;

/**
 *
 * @author i-boy
 */
public interface HsbaPhieuPhauThuatThuThuatInterface {

    public void create(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat);

    public void edit(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat);

    public String createHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList);

    public String editHsbaPhieuPhauThuatThuThuat(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat, List<DtDmNhanVien> bacsiList, List<DtDmNhanVien> bacsigmList);

    public void remove(HsbaPhieuPhauThuatThuThuat hsbaPhieuPhauThuatThuThuat);

    public HsbaPhieuPhauThuatThuThuat find(Object id);

    public List<HsbaPhieuPhauThuatThuThuat> findAll();

    public String insert(HsbaPhieuPhauThuatThuThuat obj);

    public String update(HsbaPhieuPhauThuatThuThuat obj);

    public HsbaPhieuPhauThuatThuThuat findByHsbapptttMa(String ma);

    public List<DtDmNhanVien> findBacsiByHsbapptttMa(String ma);

    public List<DtDmNhanVien> findBacsigmByHsbapptttMa(String ma);

    public HsbaPhieuPhauThuatThuThuat findByHsba(String hsbaSovaovien);

    public List<HsbaPhieuPhauThuatThuThuat> findAllByHsba(String hsbaSovaovien);

    public String getPtvByHsba(String hsbaSovaovien);

    public String getPtvByHsbappttt(HsbaPhieuPhauThuatThuThuat ppttt);

    public String getPtvByHsbappttt(String ma);
}
