/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNhikhoa;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietNhikhoaInterface {

    public void create(HsbaChiTietNhikhoa hsbaChiTietNhikhoa);

    public void edit(HsbaChiTietNhikhoa hsbaChiTietNhikhoa);

    public void remove(HsbaChiTietNhikhoa hsbaChiTietNhikhoa);

    public HsbaChiTietNhikhoa find(Object id);

    public List<HsbaChiTietNhikhoa> findAll();

    public HsbaChiTietNhikhoa findByHsbaCM(Integer hsbacmmaso) ;



}