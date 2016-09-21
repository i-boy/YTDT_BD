/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmQuanHeGiaDinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmQuanHeGiaDinhFacadeLocal {

    void create(DmQuanHeGiaDinh dmQuanHeGiaDinh);

    void edit(DmQuanHeGiaDinh dmQuanHeGiaDinh);

    void remove(DmQuanHeGiaDinh dmQuanHeGiaDinh);

    DmQuanHeGiaDinh find(Object id);

    List<DmQuanHeGiaDinh> findAll();

}


