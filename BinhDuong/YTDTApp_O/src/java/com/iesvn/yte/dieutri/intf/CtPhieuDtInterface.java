/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CtPhieuDt;
import com.iesvn.yte.dieutri.entity.PhieuDuTru;
import com.iesvn.yte.dieutri.entity.ThuocNoiTru;
import java.util.List;
import java.util.HashMap;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CtPhieuDtInterface {

    public void create(CtPhieuDt ctPhieuDt);

    public void edit(CtPhieuDt ctPhieuDt);

    public void remove(CtPhieuDt ctPhieuDt);

    public CtPhieuDt find(Object id);

    public List<CtPhieuDt> findAll();

    public java.util.List<com.iesvn.yte.dieutri.entity.CtPhieuDt> findByPhieuDuTruMa(java.lang.String phieudtMa);

    public java.lang.String capNhatPhieuDuTru(java.util.List<com.iesvn.yte.dieutri.entity.CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, com.iesvn.yte.dieutri.entity.PhieuDuTru phieuDuTru, java.lang.String phieudtMa, String priority);

    public String huyPhieuDuTru(String maPhieuDuTru);

    public String huyPhieuDuTru_New(String maPhieuDuTru);

    public String huyPhieuDuTruTra(String maPhieuDuTru);

    public String huyPhieuDuTruTra_New(String maPhieuDuTru);

    public String capNhatPhieuDuTruTra(List<CtPhieuDt> listCTPhieuDuTru, List<ThuocNoiTru> listTNT, PhieuDuTru phieuDuTru, String phieudtMa);

    public String updatePhieuDuTru(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel) ;

    public String updatePhieuDuTruLinhNGT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority);

    public String updatePhieuDuTruLinhNT(PhieuDuTru objPhieuDuTru, List<CtPhieuDt> listCtPhieuDt, List<CtPhieuDt> listCtPhieuDtDel, List<Integer> listThuocPhongKhamMa, HashMap<Integer,List> ctdt_matpk, String priority);

    public PhieuDuTru findByPhieuDuTruPhanBiet(String maPhieu, Integer phanBiet);

    public PhieuDuTru findByPhieuDuTruPhanBietKho(String maPhieu, Integer phanBiet, Integer khoNhanMaso);

    public PhieuDuTru findByPhieuDuTruKhoXuatPhanBiet(String maPhieu, Integer phanBiet, Integer khoXuatMa);

    public void removeAllCtPhieuDuTru(PhieuDuTru objPhieuDt);

    public boolean daXuatThuocTheoPhieuDT_New(String maPhieuDuTru);
}