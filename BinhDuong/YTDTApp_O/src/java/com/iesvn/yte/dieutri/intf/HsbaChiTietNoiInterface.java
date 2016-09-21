/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaChiTietNoi;
import java.util.List;

/**
 *
 * @author thanh
 */
public interface  HsbaChiTietNoiInterface {

public     void create(HsbaChiTietNoi hsbaChiTietNoi);

public     void edit(HsbaChiTietNoi hsbaChiTietNoi);

public     void remove(HsbaChiTietNoi hsbaChiTietNoi);

public     HsbaChiTietNoi find(Object id);

public     List<HsbaChiTietNoi> findAll();
 public HsbaChiTietNoi findByHsbaCM(Integer hsbacmmaso) ;
}
