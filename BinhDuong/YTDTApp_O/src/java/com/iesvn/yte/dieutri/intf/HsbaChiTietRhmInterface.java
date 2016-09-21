/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietRhm;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietRhmInterface {

    public void create(HsbaChiTietRhm hsbaChiTietRhm);

    public void edit(HsbaChiTietRhm hsbaChiTietRhm);

    public void remove(HsbaChiTietRhm hsbaChiTietRhm);

    public HsbaChiTietRhm find(Object id);

    public List<HsbaChiTietRhm> findAll();

    public HsbaChiTietRhm findByHsbaCM(Integer hsbacmmaso) ;



}