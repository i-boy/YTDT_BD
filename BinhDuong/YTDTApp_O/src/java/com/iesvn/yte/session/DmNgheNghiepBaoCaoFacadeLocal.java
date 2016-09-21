/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNgheNghiepBaoCao;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNgheNghiepBaoCaoFacadeLocal {

    void create(DmNgheNghiepBaoCao dmNgheNghiepBaoCao);

    void edit(DmNgheNghiepBaoCao dmNgheNghiepBaoCao);

    void remove(DmNgheNghiepBaoCao dmNgheNghiepBaoCao);

    DmNgheNghiepBaoCao find(Object id);

    List<DmNgheNghiepBaoCao> findAll();

}


