/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.CauHinh;
import java.util.List;


/**
 *
 * @author LENOVO 3000 Y410
 */
public interface CauHinhInterface {

public     void create(CauHinh cauHinh);

public     void edit(CauHinh cauHinh);

public     void remove(CauHinh cauHinh);

public     CauHinh find(Object id);

public     List<CauHinh> findAll();

    public java.lang.String getMaTiepDonAndMaBenhNhan();

    public java.lang.String getMaTiepDon();

}


