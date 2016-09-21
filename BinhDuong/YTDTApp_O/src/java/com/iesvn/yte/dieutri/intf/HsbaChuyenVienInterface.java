/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChuyenVien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaChuyenVienInterface {

public     void create(HsbaChuyenVien hsbaChuyenVien);

public     void edit(HsbaChuyenVien hsbaChuyenVien);

public     void remove(HsbaChuyenVien hsbaChuyenVien);

public     HsbaChuyenVien find(Object id);

public     List<HsbaChuyenVien> findAll();

    public com.iesvn.yte.dieutri.entity.HsbaChuyenVien findBySoVaoVien(java.lang.String soVaoVien);

    public String insert(HsbaChuyenVien obj);
    public String update(HsbaChuyenVien obj);
    public HsbaChuyenVien findByHsbacvMa(String ma);
    
}


