/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietTmh;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietTmhInterface {

    public void create(HsbaChiTietTmh hsbaChiTietTmh);

    public void edit(HsbaChiTietTmh hsbaChiTietTmh);

    public void remove(HsbaChiTietTmh hsbaChiTietTmh);

    public HsbaChiTietTmh find(Object id);

    public List<HsbaChiTietTmh> findAll();

    public HsbaChiTietTmh findByHsbaCM(Integer hsbacmmaso) ;



}