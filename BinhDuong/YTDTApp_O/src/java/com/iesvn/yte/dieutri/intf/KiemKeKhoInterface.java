/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.KiemKeKho;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface KiemKeKhoInterface {

public     void create(KiemKeKho kiemKeKho);

public     void edit(KiemKeKho kiemKeKho);

public     void remove(KiemKeKho kiemKeKho);

public     KiemKeKho find(Object id);

public     List<KiemKeKho> findAll();
public Number getAllKiemKeKhoTotal(String maPhieuKiem);
public List<KiemKeKho> getItemKiemKeKhos(String maPhieuKiem, int limit, int offset);

public KiemKeKho findByKhoAndMaLienKet(String maKho, String maLk);

public String updateAndEditTonKhoDau(KiemKeKho obj);

public List<KiemKeKho> findKiemKeKhoForCapNhatSL();

public List<KiemKeKho> findKiemKeKhoForCapNhatSL(String khoa, String thuoc, String nct, String nkp, String nsx, String quocgia, String namnhap, String ngay, String thang, String nam);

public List<KiemKeKho> findKiemKeKhoForCapNhatSLGUI(String makiemke, Integer loaiThuocMaso, Integer phanloaiThuocMaso, Integer thuocMaso, Integer nctMaso, Integer nkpMaso);

public String getMaPhieu();

public List<KiemKeKho> findByMaPhieuKiem(String maPhieuKiem);

public void updateKiemKeKho(List<KiemKeKho> listKiemKeKho);

}


