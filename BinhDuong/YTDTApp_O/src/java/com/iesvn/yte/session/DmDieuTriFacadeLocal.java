/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmDieuTri;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmDieuTriFacadeLocal {

    void create(DmDieuTri dmDieuTri);

    void edit(DmDieuTri dmDieuTri);

    void remove(DmDieuTri dmDieuTri);

    DmDieuTri find(Object id);

    List<DmDieuTri> findAll();

}


