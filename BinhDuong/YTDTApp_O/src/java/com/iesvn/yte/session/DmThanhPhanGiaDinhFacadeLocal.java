/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmThanhPhanGiaDinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmThanhPhanGiaDinhFacadeLocal {

    void create(DmThanhPhanGiaDinh dmThanhPhanGiaDinh);

    void edit(DmThanhPhanGiaDinh dmThanhPhanGiaDinh);

    void remove(DmThanhPhanGiaDinh dmThanhPhanGiaDinh);

    DmThanhPhanGiaDinh find(Object id);

    List<DmThanhPhanGiaDinh> findAll();

}


