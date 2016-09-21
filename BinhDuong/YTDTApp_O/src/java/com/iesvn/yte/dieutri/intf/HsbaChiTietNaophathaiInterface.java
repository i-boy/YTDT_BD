/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathai;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietNaophathaiInterface {

    public HsbaChiTietNaophathai create(HsbaChiTietNaophathai hsbaChiTietNaophathai);

    public void edit(HsbaChiTietNaophathai hsbaChiTietNaophathai);

    public void remove(HsbaChiTietNaophathai hsbaChiTietNaophathai);

    public HsbaChiTietNaophathai find(Object id);

    public List<HsbaChiTietNaophathai> findAll();

    public HsbaChiTietNaophathai findByHsbaCM(Integer hsbacmmaso) ;



}