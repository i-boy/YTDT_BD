/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuKhamChuyenKhoa;
import java.util.List;

/**
 *
 * @author James
 */
public interface PhieuKhamChuyenKhoaInterface {

    public void create(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa);

    public void edit(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa);

    public void remove(PhieuKhamChuyenKhoa phieuKhamChuyenKhoa);

    public PhieuKhamChuyenKhoa find(Object id);

    public List<PhieuKhamChuyenKhoa> findAll();

    public List<PhieuKhamChuyenKhoa> findByPhieuKhamChuyenKhoa(String maPhieu) ;

    public PhieuKhamChuyenKhoa findByMaThamKham(Integer iMaThamKham) ;

    public String capNhatPhieuKhamChuyenKhoa(PhieuKhamChuyenKhoa obj, String sMaPhieu);


}
