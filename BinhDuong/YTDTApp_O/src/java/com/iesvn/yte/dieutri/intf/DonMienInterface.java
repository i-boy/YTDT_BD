/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.dieutri.intf;

import com.iesvn.yte.dieutri.entity.DonMien;
import java.util.List;

/**
 *
 * @author LENOVO 3000 Y410
 */
public interface DonMienInterface {

public     void create(DonMien donMien);

public     void edit(DonMien donMien);

public     void remove(DonMien donMien);

public     DonMien find(Object id);

public     List<DonMien> findAll();

}


