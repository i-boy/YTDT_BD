/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNgheNghiep;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNgheNghiepFacadeLocal {

    void create(DmNgheNghiep dmNgheNghiep);

    void edit(DmNgheNghiep dmNgheNghiep);

    void remove(DmNgheNghiep dmNgheNghiep);

    DmNgheNghiep find(Object id);

    List<DmNgheNghiep> findAll();

}


