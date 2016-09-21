/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungNhan;
import java.util.List;

/**
 *
 * @author quang
 */
public interface HsbaGiayChungNhanInterface {
public     void create(HsbaGiayChungNhan hsbaGiayChungNhan);

public     void edit(HsbaGiayChungNhan hsbaGiayChungNhan);

public     void remove(HsbaGiayChungNhan hsbaGiayChungNhan);

public     HsbaGiayChungNhan find(Object id);

public     List<HsbaGiayChungNhan> findAll();

    public HsbaGiayChungNhan findBySoVaoVien(java.lang.String soVaoVien);

    public String insert(HsbaGiayChungNhan obj);
    public String update(HsbaGiayChungNhan obj);
    public HsbaGiayChungNhan findByHsbagcnMa(String ma);
}
