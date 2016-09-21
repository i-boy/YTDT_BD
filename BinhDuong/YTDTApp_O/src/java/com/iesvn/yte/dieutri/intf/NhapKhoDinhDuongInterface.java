/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.NhapKhoDinhDuong;
import java.util.Date;
import java.util.List;

/**
 *
 * @author james
 */
public interface NhapKhoDinhDuongInterface {

    public void create(NhapKhoDinhDuong nhapKhoDinhDuong);

    public void edit(NhapKhoDinhDuong nhapKhoDinhDuong);

    public void remove(NhapKhoDinhDuong nhapKhoDinhDuong);

    public NhapKhoDinhDuong find(Object id);

    public List<NhapKhoDinhDuong> findAll();

    public List<NhapKhoDinhDuong> findByDate(Date date);

    public NhapKhoDinhDuong myCreate(NhapKhoDinhDuong nhapkho);
}
