/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoitruYhct;
import java.util.List;


/**
 *
 * @author quang
 */

public interface HsbaChiTietNoitruYhctInterface {

    public void create(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct);

    public void edit(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct);

    public void remove(HsbaChiTietNoitruYhct hsbaChiTietNoitruYhct);

    public HsbaChiTietNoitruYhct find(Object id);

    public List<HsbaChiTietNoitruYhct> findAll();

    public HsbaChiTietNoitruYhct findByHsbaCM(Integer hsbacmmaso) ;



}