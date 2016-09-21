/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuKbVaoVien;
import java.util.List;

/**
 *
 * @author QuynhNhu
 */
public interface PhieuKbVaoVienInterface {

    public void create(PhieuKbVaoVien phieuKbVaoVien);

    public void edit(PhieuKbVaoVien phieuKbVaoVien);

    public void remove(PhieuKbVaoVien phieuKbVaoVien);

    public PhieuKbVaoVien find(Object id);

    public List<PhieuKbVaoVien> findAll();

    public List<PhieuKbVaoVien> findByPhieuKbVaoVien(String maPhieu);

    public PhieuKbVaoVien findByMaThamKham(Integer maThamKham);

    public String capNhatPhieuKbVaoVien(PhieuKbVaoVien obj, String sMaPhieu);
}
