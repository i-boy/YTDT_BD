/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietMat;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietMatInterface {

    public void create(HsbaChiTietMat hsbaChiTietMat);

    public void edit(HsbaChiTietMat hsbaChiTietMat);

    public void remove(HsbaChiTietMat hsbaChiTietMat);

    public HsbaChiTietMat find(Object id);

    public List<HsbaChiTietMat> findAll();

    public HsbaChiTietMat findByHsbaCM(Integer hsbacmmaso) ;



}