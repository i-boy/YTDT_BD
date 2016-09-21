/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietSosinh;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietSosinhInterface {

    public void create(HsbaChiTietSosinh hsbaChiTietSosinh);

    public void edit(HsbaChiTietSosinh hsbaChiTietSosinh);

    public void remove(HsbaChiTietSosinh hsbaChiTietSosinh);

    public HsbaChiTietSosinh find(Object id);

    public List<HsbaChiTietSosinh> findAll();

    public HsbaChiTietSosinh findByHsbaCM(Integer hsbacmmaso) ;



}