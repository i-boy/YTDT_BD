/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmKetQuaDieuTri;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmKetQuaDieuTriFacadeLocal {

    void create(DmKetQuaDieuTri dmKetQuaDieuTri);

    void edit(DmKetQuaDieuTri dmKetQuaDieuTri);

    void remove(DmKetQuaDieuTri dmKetQuaDieuTri);

    DmKetQuaDieuTri find(Object id);

    List<DmKetQuaDieuTri> findAll();

}


