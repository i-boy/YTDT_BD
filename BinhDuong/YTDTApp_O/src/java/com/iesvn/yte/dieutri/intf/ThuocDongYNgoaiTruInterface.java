/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.ThuocDongYNgoaiTru;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface ThuocDongYNgoaiTruInterface {

    public void create(ThuocDongYNgoaiTru thuocDongYNgoaiTru);

    public void edit(ThuocDongYNgoaiTru thuocDongYNgoaiTru);

    public void remove(ThuocDongYNgoaiTru thuocDongYNgoaiTru);

    public ThuocDongYNgoaiTru find(Object id);

    public List<ThuocDongYNgoaiTru> findAll();
    
    public List<ThuocDongYNgoaiTru> findByMaTiepDon(String maTiepDon) ;

    public List<ThuocDongYNgoaiTru> findByMaTiepDonAndMaBanKham(String maTiepDon, String bankhamMa, String loaiToaThuoc);

}


