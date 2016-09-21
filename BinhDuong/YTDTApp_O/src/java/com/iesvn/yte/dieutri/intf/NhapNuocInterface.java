/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.NhapNuoc;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface NhapNuocInterface {

    public void create(NhapNuoc nhapNuoc);

    public void edit(NhapNuoc nhapNuoc);

    public void remove(NhapNuoc nhapNuoc);

    public NhapNuoc find(Object id);

    public List<NhapNuoc> findAll();
    
    public List<NhapNuoc> findByNgayNhap(Date ngayNhap);
    
    public NhapNuoc findByBuongNgayNhap(Integer buongMaso, Date ngayNhap) ;
    
    public NhapNuoc myCreate(NhapNuoc nhapnuoc);
}
