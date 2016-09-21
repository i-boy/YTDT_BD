/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.XuatKhoDinhDuong;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface XuatKhoDinhDuongInterface {

    public void create(XuatKhoDinhDuong xuatKhoDinhDuong);

    public void edit(XuatKhoDinhDuong xuatKhoDinhDuong);

    public void remove(XuatKhoDinhDuong xuatKhoDinhDuong);

    public XuatKhoDinhDuong find(Object id);

    public List<XuatKhoDinhDuong> findAll();

    public List<XuatKhoDinhDuong> findByDate(Date date);

    public XuatKhoDinhDuong myCreate(XuatKhoDinhDuong xuatKho);
}
