/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsThtoan;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsThtoanInterface {

public     void create(HsThtoan hsThtoan);

public     void edit(HsThtoan hsThtoan);

public     void remove(HsThtoan hsThtoan);

public     HsThtoan find(Object id);

public     List<HsThtoan> findAll();

    public com.iesvn.yte.dieutri.entity.HsThtoan findBySovaovien(java.lang.String hsbaSovaovien);

    public java.lang.String capNhatTTRaVien(com.iesvn.yte.dieutri.entity.HsThtoan hstt, com.iesvn.yte.dieutri.entity.Hsba hsba, com.iesvn.yte.dieutri.entity.HsbaChuyenVien hsbaCV) throws java.lang.Exception;
    
public List<HsThtoan> findHsttCoThuPhi(String maSovaovien, String hoten);

public List<HsThtoan> findByNgayVaoVien(Date tungay, Date denngay) ;
public List<HsThtoan> findByNgayRaVien(Date tungay, Date denngay) ;

}


