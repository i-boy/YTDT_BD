/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.HsbaGiayChungThuong;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface HsbaGiayChungThuongInterface {

public     void create(HsbaGiayChungThuong hsbaGiayChungThuong);

public     void edit(HsbaGiayChungThuong hsbaGiayChungThuong);

public     void remove(HsbaGiayChungThuong hsbaGiayChungThuong);

public     HsbaGiayChungThuong find(Object id);

public     List<HsbaGiayChungThuong> findAll();

public String insert(HsbaGiayChungThuong gct);

public String update(HsbaGiayChungThuong gct);

public HsbaGiayChungThuong findByHsbagctMa(String ma);

}


