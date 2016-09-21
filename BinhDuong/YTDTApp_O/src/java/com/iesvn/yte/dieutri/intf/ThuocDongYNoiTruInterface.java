/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ThuocDongYNoiTru;
import java.util.List;
import java.util.Date;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ThuocDongYNoiTruInterface {

    public void create(ThuocDongYNoiTru thuocDongYNoiTru);

    public void edit(ThuocDongYNoiTru thuocDongYNoiTru);

    public void remove(ThuocDongYNoiTru thuocDongYNoiTru);

    public ThuocDongYNoiTru find(Object id);

    public List<ThuocDongYNoiTru> findAll();
    
    public List<ThuocDongYNoiTru> findBySoVaoVien(String hsbaSovaovien) ;

    public List<ThuocDongYNoiTru> findBySoVaoVienandKhoaDTandNgayandLoai(String soVaoVien, String khoaMa, Date ngay, String loai);
}


