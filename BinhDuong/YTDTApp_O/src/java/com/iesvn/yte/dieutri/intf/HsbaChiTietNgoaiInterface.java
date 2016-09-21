/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNgoai;
import java.util.List;

/**
 *
 * @author quang
 */
public interface  HsbaChiTietNgoaiInterface {

public     void create(HsbaChiTietNgoai hsbaChiTietNgoai);

public     void edit(HsbaChiTietNgoai hsbaChiTietNgoai);

public     void remove(HsbaChiTietNgoai hsbaChiTietNgoai);

public     HsbaChiTietNgoai find(Object id);

public     List<HsbaChiTietNgoai> findAll();
 public HsbaChiTietNgoai findByHsbaCM(Integer hsbacmmaso) ;
}
