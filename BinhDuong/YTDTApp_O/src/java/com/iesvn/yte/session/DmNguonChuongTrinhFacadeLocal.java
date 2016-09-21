/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.iesvn.yte.session;

import com.iesvn.yte.entity.DmNguonChuongTrinh;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author root
 */
@Local
public interface DmNguonChuongTrinhFacadeLocal {

    void create(DmNguonChuongTrinh dmNguonChuongTrinh);

    void edit(DmNguonChuongTrinh dmNguonChuongTrinh);

    void remove(DmNguonChuongTrinh dmNguonChuongTrinh);

    DmNguonChuongTrinh find(Object id);

    List<DmNguonChuongTrinh> findAll();

}


