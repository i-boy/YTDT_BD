/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.TonKhoDinhDuong;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface TonKhoDinhDuongInterface {

    public void create(TonKhoDinhDuong tonKhoDinhDuong);

    public void edit(TonKhoDinhDuong tonKhoDinhDuong);

    public void remove(TonKhoDinhDuong tonKhoDinhDuong);

    public TonKhoDinhDuong find(Object id);

    public List<TonKhoDinhDuong> findAll();
    
    public TonKhoDinhDuong findLastTKByNhaSX(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso);
    
    public int tinhSoTon(Integer loaianMaso, Integer loaian2Maso, Integer nhasxMaso, Date ngayxuat);
}
