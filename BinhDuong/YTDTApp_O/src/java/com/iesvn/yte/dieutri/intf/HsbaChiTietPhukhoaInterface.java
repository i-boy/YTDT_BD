/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietPhukhoa;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietPhukhoaInterface {

    public void create(HsbaChiTietPhukhoa hsbaChiTietPhukhoa);

    public void edit(HsbaChiTietPhukhoa hsbaChiTietPhukhoa);

    public void remove(HsbaChiTietPhukhoa hsbaChiTietPhukhoa);

    public HsbaChiTietPhukhoa find(Object id);

    public List<HsbaChiTietPhukhoa> findAll();

    public HsbaChiTietPhukhoa findByHsbaCM(Integer hsbacmmaso) ;



}