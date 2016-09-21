/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNaophathaiCt;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietNaophathaiCtInterface {

    public HsbaChiTietNaophathaiCt create(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt);

    public void edit(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt);

    public void remove(HsbaChiTietNaophathaiCt hsbaChiTietNaophathaiCt);

    public HsbaChiTietNaophathaiCt find(Object id);

    public List<HsbaChiTietNaophathaiCt> findAll();

    public List<HsbaChiTietNaophathaiCt> findByHsbaChiTietNaophathaiMaso(Integer hsbaChiTietNaophathaiMa) ;



}