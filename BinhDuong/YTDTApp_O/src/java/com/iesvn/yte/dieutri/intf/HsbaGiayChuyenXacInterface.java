/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;
import com.iesvn.yte.dieutri.entity.HsbaGiayChuyenXac;
import java.util.List;
/**
 *
 * @author quang
 */
public interface HsbaGiayChuyenXacInterface {

public     void create(HsbaGiayChuyenXac hsbaGiayChuyenXac);

public     void edit(HsbaGiayChuyenXac hsbaGiayChuyenXac);

public     void remove(HsbaGiayChuyenXac hsbaGiayChuyenXac);

public     HsbaGiayChuyenXac find(Object id);

public     List<HsbaGiayChuyenXac> findAll();
public String insert(HsbaGiayChuyenXac obj);
public String update(HsbaGiayChuyenXac obj);
public HsbaGiayChuyenXac findByHsbagcxMa(String ma);

}


