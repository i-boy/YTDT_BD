/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoaitruYhct;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietNgoaitruYhctInterface {

    public void create(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct);

    public void edit(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct);

    public void remove(HsbaChiTietNgoaitruYhct hsbaChiTietNgoaitruYhct);

    public HsbaChiTietNgoaitruYhct find(Object id);

    public List<HsbaChiTietNgoaitruYhct> findAll();

    public HsbaChiTietNgoaitruYhct findByHsbaCM(Integer hsbacmmaso) ;



}