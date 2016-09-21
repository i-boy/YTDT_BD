/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSankhoa;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietSankhoaInterface {

    public void create(HsbaChiTietSankhoa hsbaChiTietSankhoa);

    public void edit(HsbaChiTietSankhoa hsbaChiTietSankhoa);

    public void remove(HsbaChiTietSankhoa hsbaChiTietSankhoa);

    public HsbaChiTietSankhoa find(Object id);

    public List<HsbaChiTietSankhoa> findAll();

    public HsbaChiTietSankhoa findByHsbaCM(Integer hsbacmmaso) ;



}