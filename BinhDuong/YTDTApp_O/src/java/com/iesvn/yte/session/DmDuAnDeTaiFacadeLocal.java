/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDuAnDeTai;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDuAnDeTaiFacadeLocal {

    void create(DmDuAnDeTai dmDuAnDeTai);

    void edit(DmDuAnDeTai dmDuAnDeTai);

    void remove(DmDuAnDeTai dmDuAnDeTai);

    DmDuAnDeTai find(Object id);

    List<DmDuAnDeTai> findAll();

}


