/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DuTruThucPham;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface DuTruThucPhamInterface {

    public void create(DuTruThucPham duTruThucPham);

    public void edit(DuTruThucPham duTruThucPham);

    public void remove(DuTruThucPham duTruThucPham);

    public DuTruThucPham find(Object id);

    public List<DuTruThucPham> findAll();
    
    public DuTruThucPham findByLoaiTPNgayDutru(String maLoaiTP, Date ngaynhap) ;
    
    public List<DuTruThucPham> findByDate(Date date);
}
