/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.PhieuBaoAn;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface PhieuBaoAnInterface {

    public void create(PhieuBaoAn phieuBaoAn);

    public void edit(PhieuBaoAn phieuBaoAn);

    public void remove(PhieuBaoAn phieuBaoAn);

    public PhieuBaoAn find(Object id);

    public List<PhieuBaoAn> findAll();

    public List<PhieuBaoAn> findByKhoaLoaiAn(String maKhoa, String maLoaiAn);

    public String capnhatphieubaoan(List<PhieuBaoAn> listPhieuBaoAn,
            String maphieuBaoAn);

    public List<PhieuBaoAn> findByPhieuBaoAn(String maPhieu);
    
    public PhieuBaoAn findByKhoaNgayAn(String maKhoa, Date ngayAn) ;
    
    public PhieuBaoAn myCreate(PhieuBaoAn phieuBaoAn, boolean isUpdate) ;
    
    public PhieuBaoAn findByKhoaNgayAn2(String maKhoa, Date ngayAn) ;
}
